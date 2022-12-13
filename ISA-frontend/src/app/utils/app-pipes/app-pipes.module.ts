import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TransformBloodTypePipe } from './pipes/TransformBloodTypePipe';



@NgModule({
  declarations: [TransformBloodTypePipe],
  imports: [
    CommonModule
  ],
  exports:[
    TransformBloodTypePipe
  ]
})
export class AppPipesModule { }
