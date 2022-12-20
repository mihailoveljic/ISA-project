import { HttpClient, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppointmentDTO } from './calendar/appointmentDTO';

@Injectable({
  providedIn: 'root'
})
export class CalendarService {
    private apiUrl = 'http://localhost:8080/api/appointment/allAppointments'

    constructor(private http: HttpClient) { }

    public getAllAppointments(): Observable<AppointmentDTO[]>{
        return this.http.get<any>(this.apiUrl);
    }
  
}