import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AppointmentService } from '../../services/appointment.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-create-appointment',
  templateUrl: './create-appointment.component.html',
  styleUrls: ['./create-appointment.component.css'],
})
export class CreateAppointmentComponent implements OnInit {
  formGroup!: FormGroup;
  dateOfAppointment: any;
  timeOfAppointment: any = '';
  duration: any;
  description: any;
  price: any;
  appointmentDto: any;
  constructor(
      private appointmentService: AppointmentService,
      private toastr : ToastrService
      ) {}

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      dateOfAppointment: new FormControl(this.dateOfAppointment, [
        Validators.required,
      ]),
      duration: new FormControl(this.duration, [Validators.required]),
      description: new FormControl(this.description, [Validators.required]),
      price: new FormControl(this.price, [Validators.required]),
    });
  }

  createAppointment() {
    this.bindPropertiesFromFormControl();
    this.appointmentService
      .createAppointment(this.appointmentDto)
      .subscribe({
        next : (result:any) => {
          this.showSuccess();
          this.timeOfAppointment="";
          this.dateOfAppointment="";
        },
        error: (e:any) => {
          if(!e.error.message) {
            this.showError("Something went wrong on the server side, please try again later", "Blood bank application")
            this.timeOfAppointment="";
            this.dateOfAppointment="";
            return;
          }
          this.showError(e.error.message, e.error.title)
          this.timeOfAppointment="";
          this.dateOfAppointment="";
        }
      })
  }

  private bindPropertiesFromFormControl() {
    let parts1 = this.timeOfAppointment.split(' ');
    let parts2 = parts1[0].split(':');
    let hours = parts2[0];
    if (parts1[1] == 'PM') {
      hours = parseInt(hours);
      hours += 12;
      hours.toString();
    }
    this.timeOfAppointment = hours + '-' + parts2[1];
    this.dateOfAppointment = this.formGroup.value.dateOfAppointment;
    this.duration = this.formGroup.value.duration;
    this.description = this.formGroup.value.description;
    this.price = this.formGroup.value.price;
    this.appointmentDto = {
      date: this.dateOfAppointment + '-' + this.timeOfAppointment,
      duration: this.duration,
      status: 4,
      description: this.description,
      price: this.price,
      bloodBankForAppointment: 1,
      staff: [1],
    };
  }
  showSuccess() {
    this.toastr.success(
      'Successfully create appointment!',
      'Blood bank application'
    );
  }
  showError(message: string, title: string) {
    this.toastr.error(message, title);
  }
}
