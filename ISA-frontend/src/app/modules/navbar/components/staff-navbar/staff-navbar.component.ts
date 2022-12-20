import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Route } from 'src/app/utils/routes';

@Component({
  selector: 'app-staff-navbar',
  templateUrl: './staff-navbar.component.html',
  styleUrls: ['./staff-navbar.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class StaffNavbarComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
  }

  logout() {
    this.authService.logout();
  }

  navigateToCreateAppointment() {
    this.router.navigate([Route.CREATE_APPOINTMENT]);
  }
  navigateToStaffInfo() {
    this.router.navigate(['staff-info']);
  }
  navigateToCenterInfo() {
    this.router.navigate(['blood-bank-center-info']);
  }
  navigateToUsersPreview(){
    this.router.navigate([Route.USER_REVIEW]);
  }
  navigateToCalendar(){
    this.router.navigate([Route.CALENDAR]);
  }
}
