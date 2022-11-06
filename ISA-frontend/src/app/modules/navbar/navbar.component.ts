import { ChangeDetectionStrategy, Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/auth/models/user';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class NavbarComponent implements OnInit {

  user?: User;
  
  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.user = this.authService.getUser();
  }

}

