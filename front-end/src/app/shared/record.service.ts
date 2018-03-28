import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { Record } from '../record';

@Injectable()
export class RecordService {
  private apiUrl = 'http://localhost:8080/record';

  constructor(
    private http: HttpClient
  ) { }

  getAll() {
    return this.http.get<Record>(this.apiUrl)
      .map(response => response);
  }

}
