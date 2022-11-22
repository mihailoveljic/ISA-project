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

  constructor() { }

  ngOnInit(): void {
  }

}
