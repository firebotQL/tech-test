package skybet.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import skybet.form.domain.Record;
import skybet.form.repository.RecordRepository;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    RecordRepository recordRepository;

    public List<Record> findAllRecords() {
        return recordRepository.findAll();
    }
}
