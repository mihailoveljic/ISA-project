import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-thank-you',
  templateUrl: './thank-you.component.html',
  styleUrls: ['./thank-you.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ThankYouComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
