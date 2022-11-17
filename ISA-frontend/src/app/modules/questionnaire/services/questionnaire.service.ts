import { environment } from 'src/environments/environment';
import { Questionnaire } from './../../../models/Questionnaire';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {

  constructor(private http: HttpClient) { }

  create(questionnaire: Questionnaire){
    return this.http.post(`${environment.bloodBank}/api/questionnaire/create`, questionnaire);
  }

  checkCompleted(userEmail: string){
    return this.http.get(`${environment.bloodBank}/api/questionnaire/check-completed/` + userEmail);
  }
}
