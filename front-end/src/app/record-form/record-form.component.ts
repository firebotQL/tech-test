import { Component, OnInit } from '@angular/core';
import { RecordService } from '../shared/record.service';
import { UUID } from 'angular2-uuid';

@Component({
  selector: 'app-record-form',
  templateUrl: './record-form.component.html',
  styleUrls: ['./record-form.component.css'],
  providers: [RecordService]
})
export class RecordFormComponent implements OnInit {

  records = null;

  constructor(
    private recordService: RecordService
  ) {
    this.loadRecords();
  }

  ngOnInit() {
  }

  removeRecord(index) {
    if (index !== -1) {
      this.records.splice(index, 1);
    }
  }

  updateRecord(index) {
    this.recordService.save(this.records[index]);
  }

  addRecord() {
    this.records.push(this.recordService.create(UUID.UUID(), '', ''));
  }

  loadRecords() {
    this.recordService.getAll().subscribe(data => this.records = data);
  }

  postRecords() {
    this.recordService.saveAll(this.records);
  }

  onSubmit() {
    this.postRecords();
  }
}
