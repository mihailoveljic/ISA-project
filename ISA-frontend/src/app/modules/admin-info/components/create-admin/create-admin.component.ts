import { Component, OnInit} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/auth/models/user';
import { AdminInfo } from '../../model/Admin';
import { AdminInfoService } from '../../service/create-admin.service';
@Component({
  selector: 'app-create-admin',
  templateUrl: './create-admin.component.html',
  styleUrls: ['./create-admin.component.css']
})
export class CreateAdminComponent implements OnInit{
  formGroup1!: FormGroup;
  formGroup2!: FormGroup;
  user?: User;
  adminInfo: AdminInfo = {name: '', id: 0, surname: '', email: '', jmbg: '',
                          phoneNumber: '', gender: '', profession: '', professionInfo: '',
                          number: '', street: '', city: '', country: ''};

  constructor(private toastr : ToastrService, private adminInfoService: AdminInfoService) { }
  ngOnInit(): void {
    this.formGroup1 = new FormGroup({
      name: new FormControl(),
      surname: new FormControl(),
      jmbg: new FormControl(this.adminInfo.jmbg, [Validators.minLength(13)]),
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
      center: new FormControl()
    });
    
  }
  createAdminAccount(): void{
    console.log(this.adminInfo)
    this.adminInfoService.create(this.adminInfo).subscribe({
      next: (si) => {
        this.showSuccess('Successfully created admin account');
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
