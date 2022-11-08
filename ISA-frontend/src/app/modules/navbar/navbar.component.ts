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

    // ovo zakomentarisi da ne radis zahtev za auth
    this.user = this.authService.getUser();

    // ovo dodaj da mockujes ulogovanog korisnika
//     this.user = {
//      email: 'user@email.com',
//      roles: ['user'], // admin ili staff
//      jmbg: '2131232134124',
//      firstName: 'Userko',
//      lastName: 'Userkic',
//      gender: 'male',
//      phoneNumber: '1231421412',
//      address: {
//          country: 'Userska 17',
//          city: 'Usergrad',
//          street: 'Userland',
//      },
//      profession: 'Profesionalni user',
//      professionInfo: 'User company d.o.o.',
//     }
//   }

  }

}