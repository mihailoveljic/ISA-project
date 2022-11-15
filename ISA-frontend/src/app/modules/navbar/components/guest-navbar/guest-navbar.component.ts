import { Route } from 'src/app/utils/routes';
import { Router } from '@angular/router';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-guest-navbar',
  templateUrl: './guest-navbar.component.html',
  styleUrls: ['./guest-navbar.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class GuestNavbarComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router
    ) { }

  ngOnInit(): void {
  }

  navigateToCenters(){
    this.router.navigate([Route.CENTERS]);
  }

  login() {
    this.authService.login();
  }
}
