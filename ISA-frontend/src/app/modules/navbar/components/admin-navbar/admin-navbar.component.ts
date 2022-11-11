import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Router } from '@angular/router';
import { Route } from 'src/app/utils/routes';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AdminNavbarComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  logout() {
    this.authService.logout();
  }
  navigateToCreateBloodCenter(){
    this.router.navigate([Route.CREATE_CENTER]);
  }

}
