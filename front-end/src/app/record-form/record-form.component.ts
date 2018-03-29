import { Component, OnInit } from '@angular/core';
import { RecordService } from '../shared/record.service';

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
