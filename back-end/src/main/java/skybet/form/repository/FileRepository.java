package skybet.form.repository;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;
import skybet.form.domain.Record;

public abstract class FileRepository {

    protected FlatFileItemReader<Record> getReader() {
        FlatFileItemReader<Record> reader = new FlatFileItemReader<>();
        reader.setResource(getResource());

        reader.setLineMapper(new DefaultLineMapper<Record>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(getNames());
                    }
                });

                setFieldSetMapper(new BeanWrapperFieldSetMapper<Record>() {
                    {
                        setTargetType(Record.class);
                    }
                });
            }
        });

        return reader;
    }

    protected FlatFileItemWriter<Record> getWriter() {
        FlatFileItemWriter<Record> writer = new FlatFileItemWriter<>();
        writer.setResource(getResource());

        DelimitedLineAggregator<Record> aggregator = new DelimitedLineAggregator<>();

        BeanWrapperFieldExtractor extractor = new BeanWrapperFieldExtractor();
        extractor.setNames(getNames());
        aggregator.setFieldExtractor(extractor);

        writer.setLineAggregator(aggregator);

        return writer;
    }

    public abstract Resource getResource();

    public abstract String[] getNames();
}
