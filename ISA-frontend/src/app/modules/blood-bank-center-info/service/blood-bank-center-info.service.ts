import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CenterInfo } from '../model/CenterInfo';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class BloodBankCenterInfoService {
  private apiUrl = 'http://localhost:8080/api/blood-bank'


  constructor(private http: HttpClient) { }

  public get(email: any): Observable<CenterInfo> {
    return this.http.get<CenterInfo>(this.apiUrl, { params: new HttpParams().set('email', email)});
  }

  public update(centerInfo: CenterInfo): Observable<CenterInfo> {
    return this.http.put<CenterInfo>(this.apiUrl, centerInfo, httpOptions);
  }
}
