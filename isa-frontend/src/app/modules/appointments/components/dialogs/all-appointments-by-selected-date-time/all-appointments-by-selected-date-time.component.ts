import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/auth/models/user';
import { AuthService } from 'src/app/auth/services/auth.service';
import { QuestionnaireService } from 'src/app/modules/questionnaire/services/questionnaire.service';
import { Route } from 'src/app/utils/routes';
import { AppointmentService } from '../../../services/appointment.service';

@Component({
  selector: 'app-all-appointments-by-selected-date-time',
  templateUrl: './all-appointments-by-selected-date-time.component.html',
  styleUrls: ['./all-appointments-by-selected-date-time.component.css']
})
export class AllAppointmentsBySelectedDateTimeComponent implements OnInit {

  rating: number = 0;
  user?: User;
  displayedColumns: string[] = [
    'date',
    'duration',
    'description',
    'price',
    'bloodBankForAppointmentName',
    'bloodBankForAppointmentPlace',
    'bloodBankForAppointmentRating',
    'staff',
    'buttons',
  ];
  dataSource = new MatTableDataSource<any>();
  constructor(
    public dialogRef: MatDialogRef<AllAppointmentsBySelectedDateTimeComponent>,
    public dialog: MatDialog,
    private appointmentService: AppointmentService,
    private toastrService: ToastrService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private questionnaireService: QuestionnaireService,
    private authService: AuthService,
    private router: Router
  ) { 
    this.user = this.authService.getUser();
  }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource(this.data);
  }
  closeDialog(appointment: any): any {
    if(this.user){
      this.questionnaireService.checkCompleted(this.user?.email).subscribe({
        next: (result: any) => {
          if(result == true) {
            this.appointmentService.scheduleAppointment(appointment).subscribe({
              next: (result:any) => {
                if(result){
                  this.showSuccess();
                }
              },
              error: (e:any) => {
                this.toastrService.error(e.message);
              }
            });
            this.dialogRef.close();
          } else {
            var isConfirmed = confirm("You did not fulfill questionnaire, you need to fulfill it so you can schedule appointment. Let's do it right now?")
            if(isConfirmed) {
              this.dialogRef.close();
              this.router.navigate([Route.QUESTIONNAIRE]);
            }
          }
        },
        error: (e: any) => {
          this.toastrService.error(e.message);
        }
      })
    } else this.toastrService.info("Something wrong with logged in user :/")
  }
  showSuccess() {
    this.toastrService.success(
      'Successfully scheduled appointment!',
      'Blood bank application'
    );
  }
  ratingChange(event: any) {
    this.rating = event.rating;
    this.dataSource.data = this.data;
    this.dataSource.data = this.dataSource.data.filter(c => c.bloodBankForAppointment.averageRating >= this.rating);
  }
}
