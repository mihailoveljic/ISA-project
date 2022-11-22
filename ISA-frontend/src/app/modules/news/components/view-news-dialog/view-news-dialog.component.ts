import { ChangeDetectionStrategy, Component, Inject, Input, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { News } from '../../news.component';

export interface DialogData {
  news: News;
}

@Component({
  selector: 'app-view-news-dialog',
  templateUrl: './view-news-dialog.component.html',
  styleUrls: ['./view-news-dialog.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ViewNewsDialogComponent implements OnInit {

  news: News;

  constructor(
    public dialogRef: MatDialogRef<ViewNewsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) { 
    this.news = this.data.news;
  }

  ngOnInit(): void {
  }

  closeDialog(){
    this.dialogRef.close();
  }

}
