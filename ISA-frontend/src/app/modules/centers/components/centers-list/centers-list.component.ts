import { AuthService } from 'src/app/auth/services/auth.service';
import { User } from 'src/app/auth/models/user';
import { BloodBankService } from './../../services/blood-bank.service';
import { Center } from './../../../../models/Center';
import { AfterViewInit, ChangeDetectionStrategy, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-centers-list',
  templateUrl: './centers-list.component.html',
  styleUrls: ['./centers-list.component.css'],
})
export class CentersListComponent implements OnInit, AfterViewInit {
  
  centers: Array<Center> = new Array<Center>();
  user?: User;

  // constructor(private bloodBankService: BloodBankService,
  //   private authService: AuthService) { }

  // ngOnInit(): void {
  //   this.user = this.authService.getUser();
  //   this.getAllCenters();
  // }

  // getAllCenters(){
  //   this.bloodBankService.getAll().subscribe((response: any) =>{
  //     this.centers = response.bloodBanks;
  //   });
  // }

  displayedColumns: string[] = ['name', 'description', 'averageRating', 'startTime', 'endTime', 'street', 'number', 'city', 'country'];
  dataSource: MatTableDataSource<Center>;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private bloodBankService: BloodBankService,
    private authService: AuthService
    ) {
    this.dataSource = new MatTableDataSource(this.centers);
  }

  ngOnInit(): void {
      this.user = this.authService.getUser();
      this.getAllCenters();
  }
  
  getAllCenters(){
    this.bloodBankService.getAll().subscribe((response: any) =>{
      this.centers = response.bloodBanks;
      this.dataSource = new MatTableDataSource(this.centers);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}