import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateAppointmentComponent } from './components/create-appointment/create-appointment.component';
import { MaterialModule } from '../angular-material/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { ScheduleAppointmentComponent } from './components/schedule-appointment/schedule-appointment.component';
import { BrowserModule } from '@angular/platform-browser';
import { ToastrModule } from 'ngx-toastr';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  declarations: [CreateAppointmentComponent, ScheduleAppointmentComponent],
  imports: [
    CommonModule,
    BrowserModule,
    MaterialModule,
    ReactiveFormsModule,
    MatInputModule,
    BrowserAnimationsModule,
    FormsModule,
    NgxMaterialTimepickerModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      positionClass: 'toast-top-right',
    }),
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppointmentsModule {}
