import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppointmentDTO } from '../../calendar/calendar/appointmentDTO';
import { PersonInfo } from '../../staff-info/model/PersonInfo';

@Injectable({
  providedIn: 'root'
})
export class UserReviewService {
  private apiUrl = 'http://localhost:8080/api/person'
  private apiUrlAppointment = 'http://localhost:8080/api/appointment/user-appointments'


  constructor(private http: HttpClient) { }

  public getAllPersons(): Observable<PersonInfo[]>{
    return this.http.get<PersonInfo[]>(this.apiUrl);
  }

    public getUserAppointments(email: string): Observable<AppointmentDTO[]>{
        return this.http.get<AppointmentDTO[]>(this.apiUrlAppointment + '/' + email);
    }
}
