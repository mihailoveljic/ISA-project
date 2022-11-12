import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppointmentsModule } from './modules/appointments/appointments.module';
import { MaterialModule } from './modules/angular-material/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { AuthModule } from './auth/auth.module';
import { OAuthModule } from 'angular-oauth2-oidc';
import { ToastrModule } from 'ngx-toastr';
import { MatInputModule } from '@angular/material/input';
import { NavbarModule } from './modules/navbar/navbar.module';
import { StaffInfoComponent } from './modules/staff-info/component/staff-info/staff-info.component';
import { MatSelectModule } from '@angular/material/select';
import { BloodBankCenterInfoComponent } from './modules/blood-bank-center-info/component/blood-bank-center-info/blood-bank-center-info.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import {CentersModule} from './modules/centers/centers.module';
import { CreateStaffComponent } from './modules/staff-info/component/create-staff/create-staff.component';


@NgModule({
  declarations: [AppComponent, StaffInfoComponent, BloodBankCenterInfoComponent, CreateStaffComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    OAuthModule.forRoot(),
    AuthModule,
    NavbarModule,
    HttpClientModule,
    AppRoutingModule,
    AppointmentsModule,
    MaterialModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    ReactiveFormsModule,
    FormsModule,
    NgxMaterialTimepickerModule, 
    HttpClientModule,
    NgxMaterialTimepickerModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
    }),
    CentersModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
