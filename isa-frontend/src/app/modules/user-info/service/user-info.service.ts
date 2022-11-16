import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";

const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }
  
  @Injectable({
    providedIn: 'root'
  })
  export class UserInfoService {
    private apiUrl = 'http://localhost:8080/api/user'
    
    constructor(private http: HttpClient) { }
  
    public get(email: any) : any {
      return this.http.get<any>(this.apiUrl, { params: new HttpParams().set('email', email)});
    }
}