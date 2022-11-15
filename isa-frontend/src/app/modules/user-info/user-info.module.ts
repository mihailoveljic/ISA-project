import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../angular-material/angular-material.module';
import { UserInfoComponent } from './components/user-info/user-info.component';
import { NavbarModule } from '../navbar/navbar.module';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [UserInfoComponent],
  imports: [
    CommonModule,
    MaterialModule,
    NavbarModule,
    ReactiveFormsModule
  ]
})
export class UserInfoModule { }
