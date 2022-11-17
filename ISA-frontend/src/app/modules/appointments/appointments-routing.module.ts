import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HasStaffRoleGuard } from 'src/app/auth/guards/has-staff-role.guard';
import { HasUserRoleGuard } from 'src/app/auth/guards/has-user-role.guard';
import { Route } from 'src/app/utils/routes';
import { AppointmentsComponent } from './appointments.component';
import { CreateAppointmentComponent } from './components/create-appointment/create-appointment.component';
import { ScheduleAppointmentComponent } from './components/schedule-appointment/schedule-appointment.component';

const routes: Routes = [
  { path: '', component: AppointmentsComponent },
  // { path: Route.CREATE_APPOINTMENT, component: CreateAppointmentComponent, canActivate: [HasStaffRoleGuard]},
  // { path: Route.SCHEDULE_APPOINTMENT, component: ScheduleAppointmentComponent, canActivate: [HasUserRoleGuard]},
  { path: Route.CREATE_APPOINTMENT, component: CreateAppointmentComponent},
  { path: Route.SCHEDULE_APPOINTMENT, component: ScheduleAppointmentComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppointmentsRoutingModule { }
