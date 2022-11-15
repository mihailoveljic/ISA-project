import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/auth/models/user';
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

  navigateToScheduleAppointment() {
    this.router.navigate([Route.SCHEDULE_APPOINTMENT]);
  }

  navigateToCenters(){
    this.router.navigate([Route.CENTERS]);
  }
  navigateToQuestionnaire(){
    this.router.navigate([Route.QUESTIONNAIRE]);
  }
  logout() {
    this.authService.logout();
  }
}
