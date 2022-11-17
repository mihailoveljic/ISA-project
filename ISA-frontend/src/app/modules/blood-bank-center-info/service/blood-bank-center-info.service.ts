import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodSample } from '../model/BloodSample';
import { CenterInfo } from '../model/CenterInfo';
import { StaffMainInfo } from '../model/StaffMainInfo';

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
  private getAllBlodBanksUrl = 'http://localhost:8080/api/blood-bank/get-all'
  private colleaguesApi = 'http://localhost:8080/api/staff/colleagues'
  private samplesApi = 'http://localhost:8080/api/blood-sample/supplies'


  constructor(private http: HttpClient) { }

  public get(email: any): Observable<CenterInfo> {
    return this.http.get<CenterInfo>(this.apiUrl, { params: new HttpParams().set('email', email)});
  }

  public getColleagues(email: any): Observable<StaffMainInfo[]> {
    return this.http.get<StaffMainInfo[]>(this.colleaguesApi, { params: new HttpParams().set('email', email)});
  }

  public getSamples(email: any): Observable<BloodSample[]> {
    return this.http.get<BloodSample[]>(this.samplesApi, { params: new HttpParams().set('email', email)});
  }

  public update(centerInfo: CenterInfo): Observable<CenterInfo> {
    return this.http.put<CenterInfo>(this.apiUrl, centerInfo, httpOptions);
  }

  public create(centerInfo: CenterInfo): Observable<CenterInfo> {
    return this.http.post<CenterInfo>(this.apiUrl, centerInfo, httpOptions);
  }

  public getAllBloodBanks(): Observable<CenterInfo[]>{
      return this.http.get<CenterInfo[]>(this.getAllBlodBanksUrl);
  }
}
