import { Component, OnInit } from '@angular/core';
import { AppointmentService } from '../../services/appointment.service';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Route } from 'src/app/utils/routes';

@Component({
  selector: 'app-schedule-appointment',
  templateUrl: './schedule-appointment.component.html',
  styleUrls: ['./schedule-appointment.component.css'],
})
export class ScheduleAppointmentComponent implements OnInit {
  dataSource = new MatTableDataSource<any>();
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
  constructor(
    private appointmentService: AppointmentService,
    private toastr: ToastrService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAllFreeAppointment();
  }

  getAllFreeAppointment() {
    this.appointmentService.getAllFreeAppointments().subscribe({
      next: (result: any) => {
        this.appointments = result.scheduleAppointmentDTOS;
        this.dataSource.data = this.appointments;
      },
      error: (e: any) => {
        this.showError(e.message, e.message);
        console.log(e);
      },
    });
  }
  scheduleAppointment(appointment: any) {
    this.appointmentService.scheduleAppointment(appointment).subscribe({
      next: (result: any) => {
        console.log(result);
        const index = this.appointments.indexOf(appointment);
        if (index > -1) {
          this.appointments.splice(index, 1);
          this.dataSource.data = this.appointments;
        }
        this.showSuccess();
      },
      error: (e: any) => {
        this.showError(e.message, e.title);
        console.log(e);
      },
    });
  }
  showSuccess() {
    this.toastr.success(
      'Successfully scheduled appointment!',
      'Blood bank application'
    );
  }
  showError(message: string, title: string) {
    this.toastr.error(message, title);
  }
  goToSchedulePreferedAppointment() {
    this.router.navigate([Route.PREFERED_APPOINTMENT]);
  }
}
