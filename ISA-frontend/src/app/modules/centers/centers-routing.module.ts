import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Route } from 'src/app/utils/routes';
import { CreateCenterComponent } from './components/create-center/create-center.component';

const routes: Routes = [
  //{ path: '', component: CentersComponent },
  { path: Route.CREATE_CENTER, component: CreateCenterComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CentersRoutingModule { }