package skybet.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import skybet.form.domain.Record;

import java.util.List;

@RestController
@RequestMapping("/record")
public class FormRestController {

    @Autowired
    RecordService recordService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Record>> listAllRecords() {
        List<Record> records = recordService.findAllRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @RequestMapping(value = "/record", method = RequestMethod.POST)
    public ResponseEntity<List<Record>> saveAllRecords(@RequestBody List<Record> records) {
        recordService.saveAllRecords(records);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
