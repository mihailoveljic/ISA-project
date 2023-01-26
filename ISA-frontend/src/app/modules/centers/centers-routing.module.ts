import { HasAdminRoleGuard } from './../../auth/guards/has-admin-role.guard';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Route } from 'src/app/utils/routes';
import { CreateCenterComponent } from './components/create-center/create-center.component';
import { CentersComponent } from './centers.component';


const routes: Routes = [
  { path: '', component: CentersComponent },
  { path: Route.CREATE_CENTER, component: CreateCenterComponent, canActivate: [HasAdminRoleGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CentersRoutingModule { }
