package skybet.form.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import skybet.form.domain.Record;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RecordRepository {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ClassPathResource resource = new ClassPathResource("data-file.csv");

    private String[] personNames = Arrays.stream(Record.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new);

    public List<Record> findAll() {
        final FlatFileItemReader<Record> reader = getReader();
        reader.open(new ExecutionContext());
        List items = new ArrayList();
        Record item;
        try {
            while ((item = reader.read()) != null) {
                items.add(item);
            }
        } catch (Exception e) {
            log.error("Exception happened while reading records from the file", e);
        } finally {
            reader.close();
        }
        return items;
    }

    private FlatFileItemReader<Record> getReader() {
        FlatFileItemReader<Record> reader = new FlatFileItemReader<>();
        reader.setResource(resource);

        reader.setLineMapper(new DefaultLineMapper<Record>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(personNames);
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
}
