import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PersonInfo } from '../../staff-info/model/PersonInfo';

@Injectable({
  providedIn: 'root'
})
export class UserReviewService {
  private apiUrl = 'http://localhost:8080/api/person'

  constructor(private http: HttpClient) { }

  public getAllPersons(): Observable<PersonInfo[]>{
    return this.http.get<PersonInfo[]>(this.apiUrl);
}
}
