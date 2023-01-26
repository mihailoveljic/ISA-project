import { HasStaffOrUserRoleGuard } from './../../auth/guards/has-staff-or-user-role.guard';
import { HasUserRoleGuard } from './../../auth/guards/has-user-role.guard';
import { CenterAppointmentsComponent } from './components/center-appointments/center-appointments.component';
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
  { path: Route.CREATE_APPOINTMENT, component: CreateAppointmentComponent, canActivate: [HasStaffOrUserRoleGuard]},
  { path: Route.SCHEDULE_APPOINTMENT, component: ScheduleAppointmentComponent, canActivate: [HasUserRoleGuard]},
  { path: Route.MY_APPOINTMENTS, component: ScheduledAppointmentsComponent, canActivate: [HasUserRoleGuard]},
  { path: Route.PREFERED_APPOINTMENT, component: ScheduleAppointmentByUserPreferencesComponent, canActivate: [HasUserRoleGuard]},
  { path: Route.CENTER_APPOINTMENTS + '/:bloodBankId', component: CenterAppointmentsComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppointmentsRoutingModule { }
