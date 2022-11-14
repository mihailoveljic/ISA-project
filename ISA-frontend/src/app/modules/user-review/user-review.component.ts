import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from "@angular/material/table";
import { PersonInfo } from '../staff-info/model/PersonInfo';
import { UserReviewService } from './service/user-review.service';
@Component({
  selector: 'app-user-review',
  templateUrl: './user-review.component.html',
  styleUrls: ['./user-review.component.css']
})
export class UserReviewComponent implements OnInit {
  public dataSource = new MatTableDataSource<PersonInfo>();
  public displayedColumns = ['name', 'surname', 'jmbg'];
  public users: PersonInfo[] = [];
  public user: PersonInfo | undefined = undefined;

  constructor(private userReviewService: UserReviewService) { }

  ngOnInit(): void {
    this.userReviewService.getAllPersons().subscribe(res => {
      this.users = res;
      this.dataSource.data = this.users;
    })
  }

}
