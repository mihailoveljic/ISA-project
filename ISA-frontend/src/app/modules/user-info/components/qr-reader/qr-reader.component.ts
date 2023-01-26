import { Component, OnInit} from '@angular/core';
import {UserInfoService} from '../../service/user-info.service';
import { FormControl, FormGroup} from '@angular/forms';
import { AppointmentDTO } from '../../../calendar/calendar/appointmentDTO';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-qr-reader',
  templateUrl: './qr-reader.component.html',
  styleUrls: ['./qr-reader.component.css']
})
export class QrReaderComponent implements OnInit {
    
    public appointment: AppointmentDTO = {id:0, date:"", duration:0, description:"", user:"", email:"", appointmentStatus:""}
    public formGroup!: FormGroup;  
    public isVisible: boolean = false;
    public isDisabled: boolean = true
    constructor(private userInfoService: UserInfoService, private router: Router, private toastr : ToastrService) {  
    }  
  
    ngOnInit(): void {
      this.formGroup = new FormGroup({
        date: new FormControl(this.appointment.date),
        duration: new FormControl(this.appointment.duration ),
        status: new FormControl(this.appointment.appointmentStatus),
        user: new FormControl(this.appointment.user),
      });
      this.formGroup.get('date')?.disable()
      this.formGroup.get('duration')?.disable()
      this.formGroup.get('user')?.disable()
      this.formGroup.get('status')?.disable()
    }
    uploadQRCode(event: any) {
      const file = event.target.files[0];
      this.userInfoService.postImage(file).subscribe({next: (res: any) => 
        { 
          console.log(res)
          this.appointment = res
          this.appointment.date = res.date.replace("T", " ")
          this.formGroup = new FormGroup({
            date: new FormControl(this.appointment.date),
            duration: new FormControl(this.appointment.duration ),
            status: new FormControl(this.appointment.appointmentStatus),
            user: new FormControl(this.appointment.user),
          });
          this.formGroup.get('duration')?.disable()
          this.formGroup.get('user')?.disable()
          this.formGroup.get('status')?.disable()
          this.formGroup.get('date')?.disable()
          this.isVisible = true
          console.log(new Date(this.appointment.date).getDate() === new Date().getDate() )
          console.log(this.appointment.appointmentStatus === 'SCHEDULED')
          this.isDisabled = !(new Date(this.appointment.date).getDate() === new Date().getDate() && this.appointment.appointmentStatus === 'SCHEDULED') 
        },
        error: (e: Error) => this.toastr.error("Slika qr koda nije validna")});
      
    }
    select(){
      if(new Date(this.appointment.date).getDate() === new Date().getDate() && this.appointment.appointmentStatus === 'SCHEDULED'){
        this.router.navigate(['/appointment'], {queryParams : {'appointment-id': this.appointment.id, 'email': this.appointment.email}})
      }
    }
}
