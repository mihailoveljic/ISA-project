import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from "@angular/material/table";
import { Router } from '@angular/router';
import { AppointmentDTO } from '../calendar/calendar/appointmentDTO';
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
  public displayedColumns = ['name', 'surname', 'jmbg', 'phoneNumber', 'email', 'type'];
  public dataSourceAppointment = new MatTableDataSource<AppointmentDTO>();
  public displayedColumnsAppointment = ['date', 'duration', 'description', 'appointmentStatus', 'start'];
  public appointments:AppointmentDTO[] = [];
  public users: PersonInfo[] = [];
  public users_filtered: PersonInfo[] = [];
  public user: PersonInfo | undefined = undefined;
  public name: string = ''
  public surname: string = ''
  public selectedUser:PersonInfo = {id:0, email:'', surname:'', name:'', jmbg:'', phoneNumber:'', country:'', street:'', city:'', number:'', type:''}

  constructor(private router: Router, private userReviewService: UserReviewService) { }

  ngOnInit(): void {
    this.userReviewService.getAllPersons().subscribe(res => {
      this.users = res;
      this.users_filtered = res;
      this.dataSource.data = this.users_filtered;
    })
  }

  search(){
    this.users_filtered = this.users.filter(u => {
      if(u.name == null || u.surname == null)
        return false
      return (u.name.toLowerCase().includes(this.name.toLowerCase()) &&
                   u.surname.toLowerCase().includes(this.surname.toLowerCase())); } )
    this.dataSource.data = this.users_filtered 
  }

  cancel(){
    this.name = ''
    this.surname = ''
    this.users_filtered = this.users
    this.dataSource.data = this.users_filtered
  }

  addSelectedUser(row:PersonInfo){
    if (row.type === 'User')
      this.selectedUser = row
      this.userReviewService.getUserAppointments(this.selectedUser.email).subscribe(res => {
        this.appointments = res;
        this.appointments.forEach(app => app.date = app.date.replace('T', ' '))
        this.dataSourceAppointment.data = this.appointments;
      })
  }

  isSelectedUser(row:PersonInfo){
    return row == this.selectedUser
  }

  check(appointment:AppointmentDTO){
    return !(new Date(appointment.date).getDate() === new Date().getDate() && appointment.appointmentStatus === 'SCHEDULED')
  }

  select(appointment:AppointmentDTO){
    if(new Date(appointment.date).getDate() === new Date().getDate() && appointment.appointmentStatus === 'SCHEDULED'){
      this.router.navigate(['/appointment'], {queryParams : {'appointment-id': appointment.id, 'email': this.selectedUser.email}})
    }
  }

}
