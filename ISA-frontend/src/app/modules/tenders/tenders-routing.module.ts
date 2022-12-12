import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TendersComponent } from './components/tenders/tenders.component';

const routes: Routes = [{ path: '', component: TendersComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TendersRoutingModule { }
