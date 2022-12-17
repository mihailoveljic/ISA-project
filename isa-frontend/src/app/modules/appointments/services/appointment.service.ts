import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AppointmentService {
  constructor(protected http: HttpClient) {}

  createAppointment(appointmentDto: any): any {
    return this.http.post<any>(
      `${environment.bloodBank}/api/appointment`,
      appointmentDto
    );
  }
  scheduleAppointment(appointmentDto: any): any {
    return this.http.put<any>(
      `${environment.bloodBank}/api/appointment/scheduleAppointment`,
      appointmentDto
    );
  }
  getAllFreeAppointments(): any {
    return this.http.get<any>(
      `${environment.bloodBank}/api/appointment/freeAppointments`
    );
  }
  searchForAppointmentsWithCertainDateTime(selectedDateTime: any):any {
    return this.http.get<any>(
      `${environment.bloodBank}/api/appointment/allAppointmentsBySelectedDateTime/` + selectedDateTime
    );
  }
}
