import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateAppointmentComponent } from './modules/appointments/components/create-appointment/create-appointment.component';
import { ScheduleAppointmentComponent } from './modules/appointments/components/schedule-appointment/schedule-appointment.component';
import { Route } from './utils/routes';

const routes: Routes = [
  { path: Route.CREATE_APPOINTMENT, component: CreateAppointmentComponent },
  { path: Route.SCHEDULE_APPOINTMENT, component: ScheduleAppointmentComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
