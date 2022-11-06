import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar.component';
import { UserNavbarComponent } from './components/user-navbar/user-navbar.component';
import { GuestNavbarComponent } from './components/guest-navbar/guest-navbar.component';
import { StaffNavbarComponent } from './components/staff-navbar/staff-navbar.component';
import { AdminNavbarComponent } from './components/admin-navbar/admin-navbar.component';



@NgModule({
  declarations: [
    NavbarComponent,
    UserNavbarComponent,
    GuestNavbarComponent,
    StaffNavbarComponent,
    AdminNavbarComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    NavbarComponent
  ]
})
export class NavbarModule { }
