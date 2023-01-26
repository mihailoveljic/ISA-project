import { HasStaffRoleGuard } from './../../auth/guards/has-staff-role.guard';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Route } from 'src/app/utils/routes';
import { CalendarComponent } from './calendar/calendar.component';


const routes: Routes = [
  { path: Route.CALENDAR, component: CalendarComponent, canActivate: [HasStaffRoleGuard]},];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CalendarRoutingModule { }