import { AuthService } from 'src/app/auth/services/auth.service';
import { User } from 'src/app/auth/models/user';
import { BloodBankService } from './../../services/blood-bank.service';
import { Center } from './../../../../models/Center';
import { AfterViewInit, ChangeDetectionStrategy, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { RatingChangeEvent } from 'angular-star-rating';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-centers-list',
  templateUrl: './centers-list.component.html',
  styleUrls: ['./centers-list.component.css'],
})
export class CentersListComponent implements OnInit, AfterViewInit {
  
  centers: Array<Center> = new Array<Center>();
  user?: User;
  rating: number = 0;
  selectedCity: string = "";
  cities: string[] = [];
  openTime: any;
  openTimeFilter: any;
  closesTime: any;
  closesTimeFilter: any;

  field: string = 'name';
  order: string = 'desc';
  page: number = 0;
  size: number = 10;
  centersWithPageable: any;

  searchbar:string="";
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
    private authService: AuthService,
    private toastr: ToastrService
    ) {
    this.dataSource = new MatTableDataSource(this.centers);
  }

  ngOnInit(): void {
      this.user = this.authService.getUser();
      this.getAllCenters();
  }
  onSortChange(event: Sort) {
    if(event.active == 'startTime' || event.active == 'endTime' || event.active == 'street' || event.active == 'number' || event.active == 'city' || event.active == 'country') return;
    this.order = event.direction;
    this.field = event.active;
    this.getAllWithPageable();
  }
  onPaginationChange(event: any) {
    this.page = event.pageIndex;
    this.size = event.pageSize
    this.getAllWithPageable();
  }
  getAllWithPageable(){
    if(this.order==''){
      this.order = 'none';
    }
    this.bloodBankService.getAllWithPageable(this.page, this.size,this.order,this.field).subscribe((response: any) =>{
      this.centersWithPageable = response.bloodBanks;
      this.getAllCities(this.centersWithPageable);
      this.dataSource = new MatTableDataSource(this.centers);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }
  getAllCenters(){
    this.bloodBankService.getAll().subscribe((response: any) =>{
      this.centers = response.bloodBanks;
      this.getAllCities(this.centers);
      this.dataSource = new MatTableDataSource(this.centers);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }
  getAllCities(centers: any) {
    centers.forEach((center: any) => {
      if(!this.cities.includes(center.city))
        this.cities.push(center.city)
    });
  }
  filterPerCity() {
    this.searchbar = this.selectedCity;
    this.applyFilter({
      target: {
        value: this.selectedCity
      }
    })
  }
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: any) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
  applyAllFilters() {
    this.dataSource.data = this.centers;
    this.dataSource.data = this.dataSource.data.filter(c => c.averageRating >= this.rating);
    this.parseTimePickers();

  }
  ratingChange(event: any) {
    this.rating = event.rating;
  }
  parseTimePickers(){
    if(!this.openTime) return;
    let parts1 = this.openTime.split(' ');
    let parts2 = parts1[0].split(':');
    let hours1 = parts2[0];
    if (parts1[1] == 'PM') {
      hours1 = parseInt(hours1);
      hours1 += 12;
    }
    if(hours1<10) hours1 = '0' + hours1;
    this.openTimeFilter = hours1 + ':' + parts2[1];
    console.log(this.openTimeFilter);
    let from = {
      hour: hours1,
      minute: parts2[1]
    }
    if(!this.closesTime) return;
    let parts3 = this.closesTime.split(' ');
    let parts4 = parts3[0].split(':');
    let hours2 = parts4[0];
    if (parts3[1] == 'PM') {
      hours2 = parseInt(hours2);
      hours2 += 12;
      hours2.toString();
    }
    
    if(hours2<10) hours2 = '0' + hours2;
    this.closesTimeFilter = hours2 + ':' + parts4[1];
    console.log(this.closesTimeFilter);
    let to = {
      hour: hours2,
      minute: parts4[1]
    }

    let filterStart = parseFloat(from.hour) + parseFloat(from.minute)/60;
    let filterEnd = parseFloat(to.hour) + parseFloat(to.minute)/60;

    if(filterStart > filterEnd){
      this.toastr.error("Invalid dates selected", "Blood bank service");
    }

    this.filterByWorkingHours(from, to);
  }

  filterByWorkingHours(from: any, to: any){
    this.dataSource.data = this.dataSource.data.filter(x =>{
      let parts1 = x.startTime.split(':');
      
      let from1 = {
        hour: parseFloat(parts1[0]),
        minute: parseFloat(parts1[1])
      }

      let parts2 = x.endTime.split(':');
      let to1 = {
        hour: parseFloat(parts2[0]),
        minute: parseFloat(parts2[1])
      }


      let filterStart = parseFloat(from.hour) + parseFloat(from.minute)/60;
      let filterEnd = parseFloat(to.hour) + parseFloat(to.minute)/60;

      let centerStart = from1.hour + from1.minute/60;
      let centerEnd = to1.hour + to1.minute/60;

      if(centerStart <= filterStart && centerEnd >= filterEnd) return true;
      return false;
        
    })
  }

  undoFilters() {
    this.rating = 0;
    this.selectedCity = "";
    this.searchbar = "";
    this.dataSource.data = this.centers;
    this.dataSource.filter = "";
    this.openTime = undefined;
    this.closesTime = undefined;
  }
}