import { AuthService } from './../../../../auth/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { AppointmentService } from '../../services/appointment.service';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Route } from 'src/app/utils/routes';
import { User } from 'src/app/auth/models/user';

@Component({
  selector: 'app-schedule-appointment',
  templateUrl: './schedule-appointment.component.html',
  styleUrls: ['./schedule-appointment.component.css'],
})
export class ScheduleAppointmentComponent implements OnInit {
  user: User | undefined;
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
    private router: Router,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.user = this.authService.getUser();
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
    if(!this.user) return;
    if(!this.user.email) return;
    
    appointment.userEmail = this.user.email;
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
