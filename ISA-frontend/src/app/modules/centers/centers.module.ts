import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreateCenterComponent } from './components/create-center/create-center.component';
import { CentersRoutingModule } from './centers-routing.module';
import { NavbarModule } from '../navbar/navbar.module';
import { MaterialModule } from '../angular-material/angular-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { NgxMaterialTimepickerModule } from 'ngx-material-timepicker';
import { MatTableModule } from '@angular/material/table';
import { CentersComponent } from './centers.component';
import { CentersListComponent } from './components/centers-list/centers-list.component';


@NgModule({
  declarations: [
    CreateCenterComponent,
    CentersComponent,
    CentersListComponent,
  ],
  imports: [
    CentersRoutingModule,
    CommonModule,
    NavbarModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule, 
    NgxMaterialTimepickerModule,
    MatTableModule,
  ],
})
export class CentersModule { }
