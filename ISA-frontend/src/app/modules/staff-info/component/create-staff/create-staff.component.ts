import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/auth/models/user';
import { AuthService } from 'src/app/auth/services/auth.service';
import { StaffInfo } from '../../model/StaffInfo';
import { StaffInfoService } from '../../service/staff-info.service';

@Component({
  selector: 'app-create-staff',
  templateUrl: './create-staff.component.html',
  styleUrls: ['./create-staff.component.css']
})
export class CreateStaffComponent implements OnInit {
  formGroup1!: FormGroup;
  formGroup2!: FormGroup;
  user?: User;
  staffInfo: StaffInfo = {name: '', id: 0, surname: '', email: '', jmbg: '',
                          phoneNumber: '', gender: '', profession: '', professionInfo: '',
                          number: '', street: '', city: '', country: ''};
  staffInfoCopy!: StaffInfo;

  constructor(private toastr : ToastrService, private staffInfoService: StaffInfoService) { }

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
  }

  createStaffAccount(): void{
    this.staffInfoService.create(this.staffInfo).subscribe({
      next: (si) => {
        this.showSuccess('Successfully created staff account');
      },
      error: (e) => this.showError(e.error.message)
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
