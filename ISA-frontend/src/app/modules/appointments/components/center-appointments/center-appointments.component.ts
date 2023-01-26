import { Route } from 'src/app/utils/routes';
import { QuestionnaireService } from './../../../questionnaire/services/questionnaire.service';
import { User } from './../../../../auth/models/user';
import { AuthService } from './../../../../auth/services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AppointmentService } from './../../services/appointment.service';
import { MatTableDataSource } from '@angular/material/table';
import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: './center-appointments.component.html',
  styleUrls: ['./center-appointments.component.css']
})
export class CenterAppointmentsComponent implements OnInit {
  user: User | undefined;
  dataSource = new MatTableDataSource<any>();
  appointments: any;
  selectedAppointment: any;
  displayedColumns: string[] = [
    'date',
    'duration',
    'description',
    'price',
    'bloodBankForAppointment',
    'staff',
    'buttons',
  ];
  state$: any;
  bloodBankId: any;
  constructor(
    private appointmentService: AppointmentService,
    private toastr: ToastrService,
    private router: Router,
    private authService: AuthService,
    public activatedRoute: ActivatedRoute,
    private questionnaireService: QuestionnaireService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.bloodBankId = params['bloodBankId'];
      });
    console.log(this.bloodBankId);
    this.user = this.authService.getUser();
    this.getAllFreeAppointmentByBloodBankId(this.bloodBankId);
  }

  getAllFreeAppointmentByBloodBankId(bloodBankId: any) {
    this.appointmentService.getAllFreeAppointmentsByBloodBankId(bloodBankId).subscribe({
      next: (result: any) => {
        this.appointments = result.scheduleAppointmentDTOS;
        this.dataSource.data = this.appointments;
      },
      error: (e: any) => {
        this.showError(e.message, e.message);
        console.log(e);
      },
    });
  }
  scheduleAppointment(appointment: any) {
    if(!this.user) return;
    if(!this.user.email) return;
    
    appointment.userEmail = this.user.email;
    if(this.user){
      this.questionnaireService.checkCompleted(this.user.email).subscribe({
        next: (result: any) => {
          if(result.completed == true) {
            if(this.user)
            this.appointmentService.checkForAppointmentInLast6Months(this.user?.email).subscribe({
              next: (result: any) => {
                if(result == false) {
                  this.appointmentService.scheduleAppointment(appointment).subscribe({
                    next: (result:any) => {
                      if(result){
                        this.showSuccess();
                      }
                    },
                    error: (e:any) => {
                      this.toastr.error(e.message);
                    }
                  });
                  return;  
                } else{
                  this.toastr.info("You already gave blood in past 6 months so we are unable to schedule you an appointment. Read more in our Privacy&Policy");
                  return;
                }
              },
              error: (e:any) => {
                this.toastr.error(e.message);
              }
            });
          } else {
            var isConfirmed = confirm("You did not fulfill questionnaire, you need to fulfill it so you can schedule appointment. Let's do it right now?")
            if(isConfirmed) {
              this.router.navigate([Route.QUESTIONNAIRE]);
            }
          }
        },
        error: (e: any) => {
          this.toastr.error(e.message);
        }
      })
    } else this.toastr.info("Something wrong with logged in user :/")
  }
  showSuccess() {
    this.toastr.success(
      'Successfully scheduled appointment!',
      'Blood bank application'
    );
  }
  showError(message: string, title: string) {
    this.toastr.error(message, title);
  }
}
