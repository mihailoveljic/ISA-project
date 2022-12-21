import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdminInfo } from '../model/Admin';

const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  @Injectable({
    providedIn: 'root'
  })
  export class AdminInfoService {
  private apiUrl = 'http://localhost:8080/api/admin'

  constructor(private http: HttpClient) { }

  public create(adminInfo: AdminInfo): Observable<AdminInfo> {
    return this.http.post<AdminInfo>(this.apiUrl, adminInfo, httpOptions);
  }


  }