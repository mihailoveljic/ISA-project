import { environment } from './../../../../environments/environment';
import { HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) { }

  create(news:any){
    return this.http.post(environment.bloodBank + '/test', news);
  }
  
}
