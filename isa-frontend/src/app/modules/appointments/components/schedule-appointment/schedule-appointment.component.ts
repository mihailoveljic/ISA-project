import { Component, OnInit } from '@angular/core';
import { AppointmentService } from '../../services/appointment.service';

@Component({
  selector: 'app-schedule-appointment',
  templateUrl: './schedule-appointment.component.html',
  styleUrls: ['./schedule-appointment.component.css'],
})
export class ScheduleAppointmentComponent implements OnInit {
  appointments: any;
  selectedAppointment: any;
  displayedColumns: string[] = [
    'date',
    'duration',
    'description',
    'price',
    'bloodBankForAppointment',
    'staff',
    'buttons',
  ];
  constructor(private appointmentService: AppointmentService) {}

  ngOnInit(): void {
    this.getAllFreeAppointment();
  }

  getAllFreeAppointment() {
    this.appointmentService
      .getAllFreeAppointments()
      .subscribe((result: any) => {
        this.appointments = result.scheduleAppointmentDTOS;
      });
  }
  scheduleAppointment(appointment: any) {
    this.appointmentService
      .scheduleAppointment(appointment)
      .subscribe((result: any) => {
        console.log(result);
      });
  }
}
