import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateAppointmentComponent } from './components/create-appointment/create-appointment.component';

@NgModule({
  declarations: [CreateAppointmentComponent],
  imports: [CommonModule],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppointmentsModule {}
