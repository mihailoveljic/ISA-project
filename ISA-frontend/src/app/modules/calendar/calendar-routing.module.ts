import { NgModule } from '@angular/core';
import { RouterModule, ROUTER_CONFIGURATION, Routes } from '@angular/router';
import { Route } from 'src/app/utils/routes';
import { CalendarComponent } from './calendar/calendar.component';


const routes: Routes = [
  { path: Route.CALENDAR, component: CalendarComponent},];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CalendarRoutingModule { }