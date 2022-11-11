import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NewsComponent } from './news.component';
import { NavbarModule } from '../navbar/navbar.module';
import { NewsListComponent } from './components/news-list/news-list.component';
import { MaterialModule } from '../angular-material/angular-material.module';
import { NewsCommandsComponent } from './components/news-commands/news-commands.component';
import { AddNewsDialogComponent } from './components/add-news-dialog/add-news-dialog.component';
import { NewsRoutingModule } from './news-routing.module';
import { MaterialElevationDirective } from '../../utils/directives/material-elevation.directive';
import { ViewNewsDialogComponent } from './components/view-news-dialog/view-news-dialog.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    NewsComponent,
    NewsListComponent,
    NewsCommandsComponent,
    AddNewsDialogComponent,
    MaterialElevationDirective,
    ViewNewsDialogComponent,
  ],
  imports: [
    CommonModule,
    NewsRoutingModule,
    NavbarModule,
    MaterialModule,
    FormsModule
  ]
})
export class NewsModule { }
