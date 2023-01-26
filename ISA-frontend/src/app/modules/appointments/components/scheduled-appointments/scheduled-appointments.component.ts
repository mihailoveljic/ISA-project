import { AuthService } from './../../../../auth/services/auth.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { AppointmentService } from '../../services/appointment.service';
import { User } from 'src/app/auth/models/user';
import { MatSort } from '@angular/material/sort';

@Component({
  templateUrl: './scheduled-appointments.component.html',
  styleUrls: ['./scheduled-appointments.component.css']
})
export class ScheduledAppointmentsComponent implements OnInit {

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
    'QR',
    'status',
    'buttons',
  ];

  @ViewChild(MatSort)
  sort!: MatSort;
  
  constructor(
    private appointmentService: AppointmentService,
    private toastr: ToastrService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.user = this.authService.getUser();
    this.getAllAppointmentsByUserEmail();
  }
  ngAfterViewInit(){
    this.dataSource.sort = this.sort;
  }

  
  getAllAppointmentsByUserEmail() {
    if(!this.user) return;
    if(!this.user.email) return;
    this.appointmentService.getAllAppointmentsByUserEmail(this.user.email).subscribe({
      next: (result: any) => {
        this.appointments =  result.scheduleAppointmentDTOS;
        let now = new Date()
        now = new Date(now.getFullYear(), now.getMonth(), now.getDate() + 1, now.getHours(), now.getMinutes(), now.getSeconds(), now.getMilliseconds())
        this.appointments.forEach((a:any) => {
          try{
            a.qr = atob(a.qr);
          }catch(e){
            console.log(e)
          }
          try{
            atob(a.qr);
          }catch(e){
            a.qr = btoa(a.qr);
          }
          a.qr = 'data:image/png;base64,' + a.qr;
          let appointmentDate = new Date(a.date);
          if(appointmentDate >= now){
            a.canCancel = true;
          }else{
            a.canCancel = false;
            a.appointmentStatus = 'DONE';
          }
        });
        this.dataSource.data = this.appointments;
      },
      error: (e: any) => {
        this.showError(e.error.Message, e.error.Title);
        console.log(e);
      },
    });
  }
  unscheduleAppointment(appointment: any) {
    this.appointmentService.unscheduleAppointment(appointment).subscribe({
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
        this.showError(e.error.message, e.error.title);
        console.log(e);
      },
    });
  }
  showSuccess() {
    this.toastr.success(
      'Successfully unscheduled appointment!',
      'Blood bank application'
    );
  }
  showError(message: string, title: string) {
    this.toastr.error(message, title);
  }
}
