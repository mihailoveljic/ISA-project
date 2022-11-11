import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Route } from './utils/routes';
import { StaffInfoComponent } from './modules/staff-info/component/staff-info/staff-info.component';
import { BloodBankCenterInfoComponent } from './modules/blood-bank-center-info/component/blood-bank-center-info/blood-bank-center-info.component';

const routes: Routes = [
  //{ path: Route.APPOINTMENTS, loadChildren: () => import('./modules/appointments/appointments.module').then(m => m.AppointmentsModule)},
  { path: Route.HOME, loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule)},
  { path: Route.CALLBACK, loadChildren: () => import('./modules/callback/callback.module').then(m => m.CallbackModule)},
  { path: 'staff-info', component: StaffInfoComponent},
  { path:'blood-bank-center-info', component: BloodBankCenterInfoComponent},
  { path: 'news', loadChildren: () => import('./modules/news/news.module').then(m => m.NewsModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
