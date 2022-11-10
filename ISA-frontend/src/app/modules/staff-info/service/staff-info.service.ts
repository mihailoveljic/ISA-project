import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StaffInfo } from '../model/StaffInfo';
import { UpdatePassword } from '../model/UpdatePassword';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class StaffInfoService {
  private apiUrl = 'http://localhost:8080/api/staff'
  private apiUrlExtended = 'http://localhost:8080/api/staff/password'

  constructor(private http: HttpClient) { }

  public get(email: any): Observable<StaffInfo> {
    return this.http.get<StaffInfo>(this.apiUrl, { params: new HttpParams().set('email', email)});
  }

  public update(staffInfo: StaffInfo): Observable<StaffInfo> {
    return this.http.put<StaffInfo>(this.apiUrl, staffInfo, httpOptions);
  }

  public updatePassword(updatePassword: UpdatePassword): Observable<UpdatePassword> {
    return this.http.put<UpdatePassword>(this.apiUrlExtended, updatePassword, httpOptions);
  }
}
