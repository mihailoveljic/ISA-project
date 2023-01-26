import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/auth/services/auth.service';
import { CalendarService } from '../calendar.service';
import { QuestionnaireDTO } from './QuestionnaireDTO';
import { StringDTO } from './StringDTO';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export class AppointmentComponent {
  formGroup!: FormGroup;
  answers: QuestionnaireDTO[] = [];
  appointmentId: number = 0;
  email:string = '';
  nextStep:boolean = false;
  check:string = '';
  amount:number = 0;
  bloodType: string = '';
  equipmentAmount1:number = 0;
  equipmentType1: string = '';
  equipmentAmount2:number = 0;
  equipmentType2: string = '';
  text:string = '';

  constructor(private toastr: ToastrService,private router: Router, private route: ActivatedRoute, private calendarService: CalendarService, private authService: AuthService) { }

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      text: new FormControl(),
      bloodType: new FormControl(),
      amount: new FormControl(),
      equipmentType1: new FormControl(),
      equipmentAmount1: new FormControl(),
      equipmentType2: new FormControl(),
      equipmentAmount2: new FormControl()
    });
    this.formGroup = new FormGroup({
      text: new FormControl(this.text, [Validators.required]),
      bloodType: new FormControl(this.bloodType, [Validators.required]),
      amount: new FormControl(this.amount, [Validators.required, Validators.min(1)]),
      equipmentType1: new FormControl(this.equipmentType1, [Validators.required]),
      equipmentAmount1: new FormControl(this.equipmentAmount1, [Validators.required, Validators.min(1)]),
      equipmentType2: new FormControl(this.equipmentType2, [Validators.required]),
      equipmentAmount2: new FormControl(this.equipmentAmount2, [Validators.required, Validators.min(1)])
    });
    this.route.queryParams
      .subscribe(params => {
        this.appointmentId = params['appointment-id'];
        this.email = params['email'];
        });
    this.calendarService.checkQuestionnaire(this.email).subscribe((res) =>{
      this.check = res.text
    })
    this.calendarService.getAnswers(this.email).subscribe((res) => {
      this.answers = res;
    })
  }

  cancelAppointment(){
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
    if(this.equipmentType1 === this.equipmentType2){
      this.showError('Same equpiment chosen multiple times')
      return
    }
    this.calendarService.finish(this.appointmentId, this.text, this.bloodType, this.amount, 
      this.equipmentType1, this.equipmentAmount1, this.equipmentType2, this.equipmentAmount2).subscribe({
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
