import { MaterialModule } from './../angular-material/angular-material.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TendersRoutingModule } from './tenders-routing.module';
import { TendersComponent } from './components/tenders/tenders.component';
import { AppPipesModule } from 'src/app/utils/app-pipes/app-pipes.module';
import { NavbarModule } from '../navbar/navbar.module';


@NgModule({
  declarations: [
    TendersComponent
  ],
  imports: [
    CommonModule,
    TendersRoutingModule,
    MaterialModule,
    AppPipesModule,
    NavbarModule
  ]
})
export class TendersModule { }
