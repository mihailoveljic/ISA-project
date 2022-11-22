import { Component, OnInit } from '@angular/core';

export interface News{
  title: string;
  text: string;
  date: Date;
}

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {

  news: Array<any> = new Array();

  constructor() {
    this.initFakeData();
   }

  ngOnInit(): void { }

  updateNews(event:any){
    this.news.push(event);
  }

  //temporary
  private initFakeData() {
    let news1: News = {
      title: 'Title',
      text: 'description.',
      date: new Date('2022-05-05')
    };

    let news2: News = {
      title: 'asdsa',
      text: 'sdadsa.',
      date: new Date('2022-11-22')
    };

    this.news.push(news1);
    this.news.push(news2);
  }

}
