import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';
import { NavbarModule } from '../navbar/navbar.module';
import { GuestHomeComponent } from './components/guest-home/guest-home.component';
import { UserHomeComponent } from './components/user-home/user-home.component';
import { StaffHomeComponent } from './components/staff-home/staff-home.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';


@NgModule({
  declarations: [
    HomeComponent,
    GuestHomeComponent,
    UserHomeComponent,
    StaffHomeComponent,
    AdminHomeComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    NavbarModule
  ]
})
export class HomeModule { }
