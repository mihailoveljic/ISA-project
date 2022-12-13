import { environment } from './../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BloodBankService {

  url: string = environment.bloodBank + '/api/blood-bank';

  constructor(private http: HttpClient) { }

  getAll(){
    return this.http.get(this.url + '/getAll');
  }
  getAllWithPageable(page:any,size:any,order:any,field:any) {
    return this.http.get(this.url + '/getAll/' + page + '/' + size + '/' + order + '/' + field);
  }
}
