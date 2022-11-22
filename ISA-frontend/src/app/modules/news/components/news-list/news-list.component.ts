import { ChangeDetectionStrategy, Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { News } from '../../news.component';
import { ViewNewsDialogComponent } from '../view-news-dialog/view-news-dialog.component';

@Component({
  selector: 'app-news-list',
  templateUrl: './news-list.component.html',
  styleUrls: ['./news-list.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class NewsListComponent implements OnInit {

  @Input()
  news!: Array<News>;

  constructor(
    private dialog: MatDialog
  ) { }

  ngOnInit(): void { }

  openViewNewsDialog(news: News){
    let dialogRef = this.dialog.open(ViewNewsDialogComponent, {
      data:{
        news: news
      },
      maxWidth:'800px',
      autoFocus: false
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
