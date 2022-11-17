import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-guest-home',
  templateUrl: './guest-home.component.html',
  styleUrls: ['./guest-home.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class GuestHomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
