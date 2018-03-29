package skybet.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skybet.form.domain.Record;
import skybet.form.repository.RecordRepositoryImpl;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    RecordRepositoryImpl recordRepository;

    public List<Record> findAllRecords() {
        return recordRepository.findAll();
    }

    public void saveAllRecords(List<Record> records) {
        recordRepository.save(records);
    }

    public void save(Record record) {
        recordRepository.save(record);
    }
}
