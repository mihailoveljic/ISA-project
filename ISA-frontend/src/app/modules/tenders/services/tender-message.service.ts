import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TenderMessageService {

  constructor(private http: HttpClient) { }


  getAll(){
    return this.http.get(environment.bloodBank + '/api/tender-message/getAll');
  }

  confirmTenderOffer(tenderOfferId: any){
    return this.http.get(environment.bloodBank + '/api/tender-message/confirm/' + tenderOfferId);
  }

  revokeTenderOffer(tenderOfferId: any){
    return this.http.get(environment.bloodBank + '/api/tender-message/revoke/' + tenderOfferId);
  }

  deleteTenderMessage(tenderOfferId: any){
    return this.http.get(environment.bloodBank + '/api/tender-message/delete/' + tenderOfferId);
  }
}
