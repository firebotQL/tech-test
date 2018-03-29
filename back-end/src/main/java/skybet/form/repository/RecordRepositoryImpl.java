package skybet.form.repository;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import skybet.form.domain.Record;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Getter
public class RecordRepositoryImpl extends FileRepository {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private ClassPathResource resource = new ClassPathResource("data-file.csv");

    private String[] names = Arrays.stream(Record.class.getDeclaredFields()).map(Field::getName).toArray(String[]::new);

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

    public void save(List<Record> records) {
        final FlatFileItemWriter<Record> writer = getWriter();
        writer.open(new ExecutionContext());
        try {
            writer.write(records);
        } catch (Exception e) {
            log.error("Exception happened while writing items to the file", e);
        } finally {
            writer.close();
        }
    }
}
