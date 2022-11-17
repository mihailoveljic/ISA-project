import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/auth/models/user';
import { AuthService } from 'src/app/auth/services/auth.service';
import { StaffInfo } from '../../model/StaffInfo';
import { UpdatePassword } from '../../model/UpdatePassword';
import { StaffInfoService } from '../../service/staff-info.service';

@Component({
  selector: 'app-staff-info',
  templateUrl: './staff-info.component.html',
  styleUrls: ['./staff-info.component.css']
})
export class StaffInfoComponent implements OnInit {

  formGroup1!: FormGroup;
  formGroup2!: FormGroup;
  formGroupPassword!: FormGroup;
  user?: User;
  staffInfo: StaffInfo = {name: '', id: 0, surname: '', email: '', jmbg: '',
                          phoneNumber: '', gender: '', profession: '', professionInfo: '',
                          number: '', street: '', city: '', country: ''};
  staffInfoCopy!: StaffInfo;
  curPassword: string = '';
  newPassword: string = '';
  repeatPassword: string = '';
  hideCur:boolean = true;
  hideNew:boolean = true;
  hideRep:boolean = true;

  constructor(private toastr : ToastrService, private authService: AuthService, private staffInfoService: StaffInfoService) { }

  ngOnInit(): void {
    this.formGroup1 = new FormGroup({
      name: new FormControl(),
      surname: new FormControl(),
      jmbg: new FormControl(),
      email: new FormControl(),
      phone: new FormControl(),
      gender: new FormControl()
    });
    this.formGroup2 = new FormGroup({
      profession: new FormControl(),
      proinfo: new FormControl(),
      num: new FormControl(),
      street: new FormControl(),
      city: new FormControl(),
      country: new FormControl(),
    });
    this.formGroupPassword = new FormGroup({
      curPassword: new FormControl(this.curPassword, [Validators.required, Validators.minLength(8)]),
      newPassword: new FormControl(this.newPassword, [Validators.required, Validators.minLength(8)]),
      repeatPassword: new FormControl(this.repeatPassword, [Validators.required, Validators.minLength(8)])
    });
    this.user = this.authService.getUser();
    this.staffInfoService.get(this.user?.email).subscribe({
      next: (si) => {
        this.staffInfo = si;
        this.staffInfoCopy = Object.assign({}, this.staffInfo);
        this.formGroup1 = new FormGroup({
          name: new FormControl(this.staffInfo.name, [Validators.required]),
          surname: new FormControl(this.staffInfo.surname, [Validators.required]),
          jmbg: new FormControl(this.staffInfo.jmbg, [Validators.required]),
          email: new FormControl(this.staffInfo.email, [Validators.required]),
          phone: new FormControl(this.staffInfo.phoneNumber, [Validators.required]),
          gender: new FormControl(this.staffInfo.gender, [Validators.required])});
          this.formGroup2 = new FormGroup({ 
          profession: new FormControl(this.staffInfo.profession, [Validators.required]),
          proinfo: new FormControl(this.staffInfo.professionInfo, [Validators.required]),
          num: new FormControl(this.staffInfo.number, [Validators.required]),
          street: new FormControl(this.staffInfo.street, [Validators.required]),
          city: new FormControl(this.staffInfo.city, [Validators.required]),
          country: new FormControl(this.staffInfo.country, [Validators.required]),
        });
      },
      error: (e) => this.showError(e.message)
    });
  }

  updateStaffInfo(){
    this.staffInfoService.update(this.staffInfo).subscribe({
      next: (si) => {
        this.showSuccess('Successfully updated staff info');
        this.staffInfoCopy = si;
      },
      error: (e) => this.showError(e.message)
    })
  }

  undoChanges(){
    this.staffInfo = Object.assign({}, this.staffInfoCopy);
  }

  updatePassword(){
    if(this.newPassword !== this.repeatPassword)
      this.showError('Wrong repeated password!')
    else{
      let updatePassword:UpdatePassword = {email: this.user?.email, password: this.curPassword, newPassword: this.newPassword}
      this.staffInfoService.updatePassword(updatePassword).subscribe({
        next: () => {
          this.showSuccess('Successfully updated password');
        },
        error: (e) => this.showError(e.error.message)
      })
      }
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
