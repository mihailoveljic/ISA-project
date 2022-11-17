import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Route } from 'src/app/utils/routes';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css']
})
export class AppointmentsComponent implements OnInit {

  constructor(private router: Router) {}

  ngOnInit(): void {}

  navigateToScheduleAppointment() {
    this.router.navigate([Route.SCHEDULE_APPOINTMENT]);
  }

  navigateToCreateAppointment() {
    this.router.navigate([Route.CREATE_APPOINTMENT]);
  }
  
}
