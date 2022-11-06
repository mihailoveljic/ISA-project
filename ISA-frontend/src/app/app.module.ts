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

@NgModule({
  declarations: [AppComponent],
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
    MatInputModule,
    ReactiveFormsModule,
    FormsModule,
    NgxMaterialTimepickerModule, 
    HttpClientModule,
    NgxMaterialTimepickerModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
    }),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
