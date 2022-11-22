import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';
import { MatDialogRef } from '@angular/material/dialog';
import { News } from '../../news.component';

@Component({
  selector: 'app-add-news-dialog',
  templateUrl: './add-news-dialog.component.html',
  styleUrls: ['./add-news-dialog.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AddNewsDialogComponent implements OnInit {

  news: News ={
    title: '',
    date: new Date(),
    text: ''
  };

  constructor(
    public dialogRef: MatDialogRef<AddNewsDialogComponent>
  ) { }


  ngOnInit(): void {
  }

  selectDate(event: MatDatepickerInputEvent<any, any>){
    this.news.date = event.value;
  }

  publishNews(){
    //todo
    this.dialogRef.close()
  }

  closeDialog(){
    this.dialogRef.close();
  }
}
