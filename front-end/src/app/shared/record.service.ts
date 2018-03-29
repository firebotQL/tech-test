import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Record } from '../models/record';

@Injectable()
export class RecordService {
  private apiUrl = 'http://localhost:8080/record';

  constructor(
    private http: HttpClient
  ) { }

  create(id, firstName, sureName) {
    return new Record(id, firstName, sureName);
  }

  getAll() {
    return this.http.get<Record>(this.apiUrl)
      .map(response => response);
  }

  saveAll(records) {
    return this.http.post<Record>(this.apiUrl, records).subscribe();
  }

  save(record) {
    return this.http.put<Record>(this.apiUrl, record).subscribe();
  }

}
