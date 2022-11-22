import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
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

  news: Array<News> = new Array<News>();

  constructor(
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.initFakeData();
  }

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

  //temporary
  private initFakeData() {
    let news1: News = {
      title: 'Vest jedan',
      text: 'Ovo je tekst u kome vam objasnjavam zasto je moja vest bitna, jelte.',
      date: new Date('2022-11-11')
    };

    let news2: News = {
      title: 'Vest dva',
      text: 'Ovo je tekst u kome vasssssssssssssssssss ssssssssssssssssssssssssssssssssssssss ssssssssssssssssssssssssssss ssssssssssssssssssssssssssssm objaaaaaaaaaaaaaaaaa aaaaaaaaaaaaasnjavam zasto je moja veeeeeeeeeeeeeeeeeeeeeeeeeeeee eeeeeeeeeeeeeeeeeee eeeeeeeeeeeeeeeeeeeeeeeeeee eeeeeeeeeeeeeeeeeeee eeeeeeeeee eeeeeeeeeest bitna, jelte.',
      date: new Date('2022-11-11')
    };
    let news3: News = {
      title: 'Vest tri',
      text: 'Ovo je tekst u kome vam objasnjavam zasto je moja vest bitna, jelte.',
      date: new Date('2022-11-11')
    };
    let news4: News = {
      title: 'Vest cetiri',
      text: 'Ovo je tekst u kome vam objasnjavam zasto je moja vest bitna, jelte.',
      date: new Date('2022-11-11')
    };
    let news5: News = {
      title: 'Vest pet',
      text: 'Ovo je tekst u kome vam objasnjavam zasto je moja vest bitna, jelte.',
      date: new Date('2022-11-11')
    };
    let news6: News = {
      title: 'Vest sest',
      text: 'Ovo je tekst u kome vam objasnjavam zasto je moja vest bitna, jelte.',
      date: new Date('2022-11-11')
    };
    this.news.push(news1);
    this.news.push(news2);
    this.news.push(news3);
    this.news.push(news4);
    this.news.push(news5);
    this.news.push(news6);
  }
}
