import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/auth/services/auth.service';
import { CalendarService } from '../calendar.service';
import { StringDTO } from './StringDTO';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent {
  formGroup!: FormGroup;
  appointmentId: number = 0;
  email:string = '';
  nextStep:boolean = false;
  check:string = '';
  amount:number = 0;
  bloodType: string = '';
  text:string = '';

  constructor(private toastr: ToastrService,private router: Router, private route: ActivatedRoute, private calendarService: CalendarService, private authService: AuthService) { }

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      text: new FormControl(),
      bloodType: new FormControl(),
      amount: new FormControl()
    });
    this.formGroup = new FormGroup({
      text: new FormControl(this.text, [Validators.required]),
      bloodType: new FormControl(this.bloodType, [Validators.required]),
      amount: new FormControl(this.amount, [Validators.required, Validators.min(1)])
    });
    this.route.queryParams
      .subscribe(params => {
        this.appointmentId = params['appointment-id'];
        this.email = params['email'];
        });
    this.calendarService.checkQuestionnaire(this.email).subscribe((res) =>{
      this.check = res.text
    }
    )
  }

  cancelAppointment(){
    console.log(this.appointmentId)
    this.calendarService.cancelAppointment(this.appointmentId).subscribe(() => {
      this.showSuccess('Successfully cancelled appointment');
      this.router.navigate(['/calendar'])
   })
  }

  affirmativeAnswer(){
    this.nextStep = true;
  }

  negativeAnswer(){
    this.calendarService.didntAppear(this.appointmentId, this.email).subscribe(() => {
      this.showSuccess('Successfully cancelled appointment');
      this.router.navigate(['/calendar'])
   })
  }

  finishAppointment(){
    this.calendarService.finish(this.appointmentId, this.text, this.bloodType, this.amount).subscribe({
      next: () => {
        this.showSuccess('Successfully finished appointment');
        this.router.navigate(['/calendar']);
      },
      error: (e) => this.showError(e.error.text)
    })
  }
  showSuccess(message: string) {
    this.toastr.success(
      message,
      'Blood bank application'
    );
  }
  showError(message: string) {
    this.toastr.error(message, 'Blood bank application');
  }

}
