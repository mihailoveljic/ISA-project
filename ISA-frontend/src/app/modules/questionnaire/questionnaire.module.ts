import { MaterialModule } from './../angular-material/angular-material.module';
import { NavbarModule } from './../navbar/navbar.module';
import { NavbarComponent } from './../navbar/navbar.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { QuestionnaireRoutingModule } from './questionnaire-routing.module';
import { QuestionnaireComponent } from './questionnaire.component';
import { QuestionComponent } from './components/question/question.component';
import { QuestionsComponent } from './components/questions/questions.component';
import { ThankYouComponent } from './components/thank-you/thank-you.component';


@NgModule({
  declarations: [
    QuestionnaireComponent,
    QuestionComponent,
    QuestionsComponent,
    ThankYouComponent
  ],
  imports: [
    CommonModule,
    QuestionnaireRoutingModule,
    NavbarModule,
    MaterialModule
  ]
})
export class QuestionnaireModule { }
