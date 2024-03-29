import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Route } from 'src/app/utils/routes';

@Component({
  selector: 'app-user-navbar',
  templateUrl: './user-navbar.component.html',
  styleUrls: ['./user-navbar.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class UserNavbarComponent implements OnInit {

  
  constructor(
    private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
  }

  navigateToUserInfo() {
    this.router.navigate([Route.USER_INFO]);
  }
  navigateToScheduleAppointment() {
    this.router.navigate([Route.SCHEDULE_APPOINTMENT]);
  }

  navigateToCenters(){
    this.router.navigate([Route.CENTERS]);
  }
  navigateToQuestionnaire(){
    this.router.navigate([Route.QUESTIONNAIRE]);
  }
  navigateToMyAppointment() {
    this.router.navigate([Route.MY_APPOINTMENTS]);
    }

  logout() {
    this.authService.logout();
  }
}
