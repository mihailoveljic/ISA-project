import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Route } from '../utils/routes';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit(): void {}

  navigateToScheduleAppointment() {
    this.router.navigateByUrl(Route.SCHEDULE_APPOINTMENT);
  }

  navigateToCreateAppointment() {
    this.router.navigateByUrl(Route.CREATE_APPOINTMENT);
  }
}
