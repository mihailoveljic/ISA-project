import { ScheduledAppointmentsComponent } from './components/scheduled-appointments/scheduled-appointments.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Route } from 'src/app/utils/routes';
import { AppointmentsComponent } from './appointments.component';
import { CreateAppointmentComponent } from './components/create-appointment/create-appointment.component';
import { ScheduleAppointmentByUserPreferencesComponent } from './components/schedule-appointment-by-user-preferences/schedule-appointment-by-user-preferences.component';
import { ScheduleAppointmentComponent } from './components/schedule-appointment/schedule-appointment.component';

const routes: Routes = [
  { path: '', component: AppointmentsComponent },
  // { path: Route.CREATE_APPOINTMENT, component: CreateAppointmentComponent, canActivate: [HasStaffRoleGuard]},
  // { path: Route.SCHEDULE_APPOINTMENT, component: ScheduleAppointmentComponent, canActivate: [HasUserRoleGuard]},
  { path: Route.CREATE_APPOINTMENT, component: CreateAppointmentComponent},
  { path: Route.SCHEDULE_APPOINTMENT, component: ScheduleAppointmentComponent},
  { path: Route.MY_APPOINTMENTS, component: ScheduledAppointmentsComponent},
  { path: Route.PREFERED_APPOINTMENT, component: ScheduleAppointmentByUserPreferencesComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppointmentsRoutingModule { }
