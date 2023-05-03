import { Injectable } from '@angular/core';
import { ISelectors } from '../_model/selectors';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  })
}

@Injectable({
  providedIn: 'root'
})
export class SelectorsService {
  private apiUrl = 'http://localhost:8080/api/selectors/';

  constructor(private http: HttpClient) {}

  getSelectorData(): Observable<ISelectors> {
    return this.http.get<ISelectors>(this.apiUrl)
  }
}
