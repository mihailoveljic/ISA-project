import { ToastrService } from 'ngx-toastr';
import { NewsService } from './../../services/news.service';
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

  news: any ={
    title: '',
    date: new Date(),
    startTime: '',
    text: ''
  };

  constructor(
    public dialogRef: MatDialogRef<AddNewsDialogComponent>,
    private newsService: NewsService,
    private toastr: ToastrService
  ) { }


  ngOnInit(): void {
  }

  selectDate(event: MatDatepickerInputEvent<any, any>){
    this.news.date = event.value;
  }

  publishNews(){
    let date = new Date(this.news.date);
    let time = this.news.startTime.split(':');
    let request = {
      title: this.news.title,
      description: this.news.text,
      dateOfEvent: new Date(date.getFullYear(), date.getMonth(), date.getDate(), time[0], time[1])
    }
    this.newsService.create(request).subscribe((response:any)=>{
    });
    this.toastr.success("Successfully added news!", "News");
    this.dialogRef.close({event: 'add', data: this.news})
  }

  closeDialog(){
    this.dialogRef.close();
  }
}
