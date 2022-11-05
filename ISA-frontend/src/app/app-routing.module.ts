import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateAppointmentComponent } from './modules/appointments/components/create-appointment/create-appointment.component';
import { Route } from './utils/routes';

const routes: Routes = [
  { path: Route.CREATE_APPOINTMENT, component: CreateAppointmentComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
