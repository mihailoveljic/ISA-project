import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/auth/models/user';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Route } from 'src/app/utils/routes';
import { UserInfoService } from '../../service/user-info.service';

@Component({
  selector: 'app-staff-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  formGroup1!: FormGroup;
  formGroup2!: FormGroup;
  formGroupPassword!: FormGroup;
  user?: User;
  userInfo: any;
  userInfoCopy: any;
  curPassword: string = '';
  newPassword: string = '';
  repeatPassword: string = '';
  hideCur:boolean = true;
  hideNew:boolean = true;
  hideRep:boolean = true;
  constructor(private toastr : ToastrService, private authService: AuthService,
    private userInfoService: UserInfoService,
    private router: Router) { }

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
    this.userInfoService.get(this.user?.email).subscribe({
      next: (ui: any) => {
        this.userInfo = this.user;
        this.userInfoCopy = Object.assign({}, this.userInfo);
        this.formGroup1 = new FormGroup({
          name: new FormControl(this.user?.firstName, [Validators.required]),
          surname: new FormControl(this.user?.lastName, [Validators.required]),
          jmbg: new FormControl(this.user?.jmbg, [Validators.required]),
          email: new FormControl(this.user?.email, [Validators.required]),
          phone: new FormControl(this.user?.phoneNumber, [Validators.required]),
          gender: new FormControl(this.user?.gender, [Validators.required])});
          this.formGroup2 = new FormGroup({ 
          profession: new FormControl(this.user?.profession, [Validators.required]),
          proinfo: new FormControl(this.user?.professionInfo, [Validators.required]),
          num: new FormControl('12A', [Validators.required]),
          street: new FormControl(this.user?.address.street, [Validators.required]),
          city: new FormControl(this.user?.address.city, [Validators.required]),
          country: new FormControl(this.user?.address.country, [Validators.required]),
        });
      },
      error: (e:any) => this.showError(e.message)
    });
  }

  undoChanges(){
    this.userInfo = Object.assign({}, this.userInfoCopy);
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
