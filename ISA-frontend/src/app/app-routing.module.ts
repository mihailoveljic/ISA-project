import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Route } from './utils/routes';

const routes: Routes = [
  //{ path: Route.APPOINTMENTS, loadChildren: () => import('./modules/appointments/appointments.module').then(m => m.AppointmentsModule)},
  { path: Route.HOME, loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule)},
  { path: Route.CALLBACK, loadChildren: () => import('./modules/callback/callback.module').then(m => m.CallbackModule)},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
