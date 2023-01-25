import { MaterialModule } from './../angular-material/angular-material.module';
import { NavbarModule } from './../navbar/navbar.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { LocationTrackerRoutingModule } from './location-tracker-routing.module';
import { CheckLocationComponent } from './components/check-location/check-location.component';


@NgModule({
  declarations: [
    CheckLocationComponent,
  ],
  imports: [
    CommonModule,
    LocationTrackerRoutingModule,
    NavbarModule,
    MaterialModule,
  ]
})
export class LocationTrackerModule { }
