import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog'
import { AddNewsDialogComponent } from '../add-news-dialog/add-news-dialog.component';

@Component({
  selector: 'app-news-commands',
  templateUrl: './news-commands.component.html',
  styleUrls: ['./news-commands.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class NewsCommandsComponent implements OnInit {

  constructor(
    private dialog: MatDialog
  ) { }

  ngOnInit(): void {
  }

  openAddNewsDialog(){
    let dialogRef = this.dialog.open(AddNewsDialogComponent, {
      autoFocus: false
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }
}
