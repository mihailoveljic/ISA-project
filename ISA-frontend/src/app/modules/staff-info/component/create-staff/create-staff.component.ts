import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/auth/models/user';
import { AuthService } from 'src/app/auth/services/auth.service';
import { StaffWithCenter } from '../../model/StaffWithCenter';
import { StaffInfoService } from '../../service/staff-info.service';
import { BloodBankCenterInfoService } from 'src/app/modules/blood-bank-center-info/service/blood-bank-center-info.service';
import { CenterInfo} from 'src/app/modules/blood-bank-center-info/model/CenterInfo';


@Component({
  selector: 'app-create-staff',
  templateUrl: './create-staff.component.html',
  styleUrls: ['./create-staff.component.css']
})
export class CreateStaffComponent implements OnInit {
  formGroup1!: FormGroup;
  formGroup2!: FormGroup;
  user?: User;
  staffInfo: StaffWithCenter = {name: '', id: 0, surname: '', email: '', jmbg: '',
                          phoneNumber: '', gender: '', profession: '', professionInfo: '',
                          number: '', street: '', city: '', country: '', bloodBankId: 1};

  selectedValue!: CenterInfo;

  centers!: CenterInfo[];

  constructor(private toastr : ToastrService, private staffInfoService: StaffInfoService, private centerService: BloodBankCenterInfoService) { }

  ngOnInit(): void {
    this.formGroup1 = new FormGroup({
      name: new FormControl(),
      surname: new FormControl(),
      jmbg: new FormControl(this.staffInfo.jmbg, [Validators.minLength(8)]),
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
    this.centerService.getAllBloodBanks().subscribe({
      next: (si) => {
          this.centers = si
      }});
  }

  createStaffAccount(): void{
    if (this.selectedValue != null)
      this.staffInfo.bloodBankId = this.selectedValue.id
    console.log(this.staffInfo)
    this.staffInfoService.create(this.staffInfo).subscribe({
      next: (si) => {
        this.showSuccess('Successfully created staff account');
      },
      error: (e) => this.showError(e.error)
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
