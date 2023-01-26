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
    private appointmentUrl = 'http://localhost:8080/api/appointment'
    
    constructor(private http: HttpClient) { }
  
    public get(email: any) : any {
      return this.http.get<any>(this.apiUrl, { params: new HttpParams().set('email', email)});
    }

    public  postImage(file: File): any{
      const formData = new FormData();
        formData.append('file', file);

      return this.http.post<string>(this.appointmentUrl+'/upload', formData)
    
    }
}