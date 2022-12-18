import { User } from './../../../../auth/models/user';
import { AuthService } from './../../../../auth/services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AppointmentService } from './../../services/appointment.service';
import { MatTableDataSource } from '@angular/material/table';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './center-appointments.component.html',
  styleUrls: ['./center-appointments.component.css']
})
export class CenterAppointmentsComponent implements OnInit {
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
  state$: any;
  bloodBankId: any;
  constructor(
    private appointmentService: AppointmentService,
    private toastr: ToastrService,
    private router: Router,
    private authService: AuthService,
    public activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.bloodBankId = params['bloodBankId'];
      });
    console.log(this.bloodBankId);
    this.user = this.authService.getUser();
    this.getAllFreeAppointmentByBloodBankId(this.bloodBankId);
  }

  getAllFreeAppointmentByBloodBankId(bloodBankId: any) {
    this.appointmentService.getAllFreeAppointmentsByBloodBankId(bloodBankId).subscribe({
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
}
