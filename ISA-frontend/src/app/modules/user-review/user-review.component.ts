import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from "@angular/material/table";
import { AdminNavbarComponent } from '../navbar/components/admin-navbar/admin-navbar.component';
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
  public users_filtered: PersonInfo[] = [];
  public user: PersonInfo | undefined = undefined;
  public name: string = ''
  public surname: string = ''

  constructor(private userReviewService: UserReviewService) { }

  ngOnInit(): void {
    this.userReviewService.getAllPersons().subscribe(res => {
      this.users = res;
      this.users_filtered = res;
      this.dataSource.data = this.users_filtered;
    })
  }

  search(){
    this.users_filtered = this.users.filter(u => {return (u.name.toLowerCase().includes(this.name.toLowerCase()) &&
                   u.surname.toLowerCase().includes(this.surname.toLowerCase())); } )
    //this.treninziSaDatumom.filter(o => o.treninzi.price >= minCena && o.treninzi.price <= maxCena);
    this.dataSource.data = this.users_filtered 
  }

}
