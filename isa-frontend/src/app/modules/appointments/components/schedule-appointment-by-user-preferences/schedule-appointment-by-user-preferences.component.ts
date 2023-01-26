import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { AppointmentService } from '../../services/appointment.service';
import { AllAppointmentsBySelectedDateTimeComponent } from '../dialogs/all-appointments-by-selected-date-time/all-appointments-by-selected-date-time.component';

@Component({
  selector: 'app-schedule-appointment-by-user-preferences',
  templateUrl: './schedule-appointment-by-user-preferences.component.html',
  styleUrls: ['./schedule-appointment-by-user-preferences.component.css']
})
export class ScheduleAppointmentByUserPreferencesComponent implements OnInit {

  formGroup!: FormGroup;
  dateOfAppointment: any;
  timeOfAppointment: any = '';
  selectedDateTime: any;
  constructor(
      private appointmentService: AppointmentService,
      private toastr : ToastrService,
      public dialog: MatDialog
      ) {}

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      dateOfAppointment: new FormControl(this.dateOfAppointment, [
        Validators.required,
      ]),
    });
  }
  searchForSelectedDateTime() {
    this.bindPropertiesFromFormControl();
    this.appointmentService.searchForAppointmentsWithCertainDateTime(this.selectedDateTime).subscribe({
      next: (result:any) => {
        console.log(result);
        this.timeOfAppointment="";
        this.dateOfAppointment="";
        this.openDialogAllAppointmentsBySelectedDateTime(result.scheduleAppointmentDTOS);
      },
      error: (e: any) => {
        this.toastr.error(e.message);
        this.timeOfAppointment="";
        this.dateOfAppointment="";
      }
    });
  }
  openDialogAllAppointmentsBySelectedDateTime(allAppointmentsBySelectedDateTime: any) {
    let dialogRef = this.dialog.open(AllAppointmentsBySelectedDateTimeComponent, {
      data: allAppointmentsBySelectedDateTime
    });
    dialogRef.afterClosed().subscribe({
      next: (result:any) =>{
        if(result && result!=="no"){
        }
      }
    });
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
    this.selectedDateTime = this.dateOfAppointment + '-' + this.timeOfAppointment;
  }
}
