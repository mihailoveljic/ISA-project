import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/auth/models/user';
import { AuthService } from 'src/app/auth/services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

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
