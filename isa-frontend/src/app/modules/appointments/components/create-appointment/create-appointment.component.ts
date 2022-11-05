import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AppointmentService } from '../../services/appointment.service';

@Component({
  selector: 'app-create-appointment',
  templateUrl: './create-appointment.component.html',
  styleUrls: ['./create-appointment.component.css'],
})
export class CreateAppointmentComponent implements OnInit {
  formGroup!: FormGroup;
  dateOfAppointment: any;
  timeOfAppointment: any;
  duration: any;
  description: any;
  price: any;
  appointmentDto: any;
  constructor(private appointmentService: AppointmentService) {}

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      dateOfAppointment: new FormControl(this.dateOfAppointment, [
        Validators.required,
      ]),
      timeOfAppointment: new FormControl(this.timeOfAppointment, [
        Validators.required,
      ]),
      duration: new FormControl(this.duration, [Validators.required]),
      description: new FormControl(this.description, [Validators.required]),
      price: new FormControl(this.price, [Validators.required]),
    });
  }

  createAppointment() {
    this.bindPropertiesFromFormControl();
    console.log(this.dateOfAppointment + '-' + this.timeOfAppointment);
    // this.bindPropertiesFromFormControl();
    // this.appointmentService
    //   .createAppointment(this.appointmentDto)
    //   .subscribe((result: any) => {
    //     console.log(result);
    //   });
  }

  private bindPropertiesFromFormControl() {
    this.dateOfAppointment = this.formGroup.value.dateOfAppointment;
    this.timeOfAppointment = this.formGroup.value.timeOfAppointment;
    this.duration = this.formGroup.value.duration;
    this.description = this.formGroup.value.description;
    this.price = this.formGroup.value.price;
    this.appointmentDto = {
      date: this.dateOfAppointment + '-' + this.timeOfAppointment,
      duration: this.duration,
      status: 2,
      description: this.description,
      price: this.price,
      bloodBankForAppointment: 1,
      staff: 1,
    };
  }
}
