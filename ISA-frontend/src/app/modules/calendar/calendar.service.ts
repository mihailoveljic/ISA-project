import { HttpClient, HttpParams, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QuestionnaireDTO } from './appointment/QuestionnaireDTO';
import { StringDTO } from './appointment/StringDTO';
import { AppointmentDTO } from './calendar/appointmentDTO';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {
    private apiUrl = 'http://localhost:8080/api/appointment/allAppointments'
    private apiUrlCancel = 'http://localhost:8080/api/appointment/cancel'
    private apiUrlFinish = 'http://localhost:8080/api/appointment/finish'
    private apiUrlDidntAppear = 'http://localhost:8080/api/appointment/didnt-appear'
    private apiUrlCheck = 'http://localhost:8080/api/questionnaire/check'
    private apiUrlUserAnswers = 'http://localhost:8080/api/questionnaire'


    constructor(private http: HttpClient) { }

    public getAllAppointments(): Observable<AppointmentDTO[]>{
        return this.http.get<any>(this.apiUrl);
    }

    public cancelAppointment(id: number): Observable<StringDTO>{
      return this.http.get<StringDTO>(this.apiUrlCancel, { params: new HttpParams().set('id', id)});
    }

    public didntAppear(id: number, email: string): Observable<StringDTO>{
      return this.http.get<StringDTO>(this.apiUrlDidntAppear, { params: new HttpParams().set('id', id).set('email',email)});
    }

    public checkQuestionnaire(email: string): Observable<StringDTO>{
      return this.http.get<StringDTO>(this.apiUrlCheck, { params: new HttpParams().set('email', email)});
    }
    public getAnswers(email: string): Observable<QuestionnaireDTO[]>{
      return this.http.get<QuestionnaireDTO[]>(this.apiUrlUserAnswers + '/' + email);
    }

    public finish(id: number, text:string, bloodType:string, amount:number): Observable<StringDTO>{
      return this.http.get<StringDTO>(this.apiUrlFinish, { params: new HttpParams().set('id', id).set('text', text).set('bloodType', bloodType).set('amount',amount)});
    }
  
}