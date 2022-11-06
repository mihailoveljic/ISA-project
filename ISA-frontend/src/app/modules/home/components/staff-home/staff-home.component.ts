import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-staff-home',
  templateUrl: './staff-home.component.html',
  styleUrls: ['./staff-home.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class StaffHomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
