package skybet.form.repository;

import org.springframework.stereotype.Repository;
import skybet.form.domain.Record;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecordRepository {

    public List<Record> findAll() {
        return new ArrayList<>();
    }
}
