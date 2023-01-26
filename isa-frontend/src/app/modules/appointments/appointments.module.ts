import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppointmentsRoutingModule } from './appointments-routing.module';
import { AppointmentsComponent } from './appointments.component';
import { MaterialModule } from '../angular-material/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { MatTableModule } from '@angular/material/table';
import { ScheduleAppointmentComponent } from './components/schedule-appointment/schedule-appointment.component';
import { CreateAppointmentComponent } from './components/create-appointment/create-appointment.component';
import { NavbarModule } from '../navbar/navbar.module';
import { ScheduledAppointmentsComponent } from './components/scheduled-appointments/scheduled-appointments.component';
import { ScheduleAppointmentByUserPreferencesComponent } from './components/schedule-appointment-by-user-preferences/schedule-appointment-by-user-preferences.component';
import { AllAppointmentsBySelectedDateTimeComponent } from './components/dialogs/all-appointments-by-selected-date-time/all-appointments-by-selected-date-time.component';
import { StarRatingModule } from 'angular-star-rating';
import { CenterAppointmentsComponent } from './components/center-appointments/center-appointments.component';


@NgModule({
  declarations: [
    AppointmentsComponent,
    ScheduleAppointmentComponent,
    CreateAppointmentComponent,
    ScheduledAppointmentsComponent,
    ScheduleAppointmentByUserPreferencesComponent,
    AllAppointmentsBySelectedDateTimeComponent,
    CenterAppointmentsComponent,
  ],
  imports: [
    CommonModule,
    AppointmentsRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
    MatInputModule,
    FormsModule,
    NgxMaterialTimepickerModule,
    MatTableModule,
    StarRatingModule,
    NavbarModule
  ]
})
export class AppointmentsModule { }
