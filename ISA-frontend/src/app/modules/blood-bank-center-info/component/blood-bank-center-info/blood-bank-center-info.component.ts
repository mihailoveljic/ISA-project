import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/auth/models/user';
import { AuthService } from 'src/app/auth/services/auth.service';
import { CenterInfo } from '../../model/CenterInfo';
import { BloodBankCenterInfoService } from '../../service/blood-bank-center-info.service';

@Component({
  selector: 'app-blood-bank-center-info',
  templateUrl: './blood-bank-center-info.component.html',
  styleUrls: ['./blood-bank-center-info.component.css']
})
export class BloodBankCenterInfoComponent implements OnInit {

  formGroup1!: FormGroup;
  formGroup2!: FormGroup;
  user?: User;
  centerInfo: CenterInfo = {name: '', id: 0, description: '', averageRating: 0, startTime: '', endTime: '', 
                          number: '', street: '', city: '', country: ''};
  centerInfoCopy!: CenterInfo;

  constructor(private toastr : ToastrService, private authService: AuthService, private centerInfoService: BloodBankCenterInfoService) { }

  ngOnInit(): void {
    this.formGroup1 = new FormGroup({
      name: new FormControl(),
      description: new FormControl(),
      rating: new FormControl(),
      start: new FormControl(),
      end: new FormControl()
    });
    this.formGroup2 = new FormGroup({
      num: new FormControl(),
      street: new FormControl(),
      city: new FormControl(),
      country: new FormControl(),
    });
    this.user = this.authService.getUser();
    this.centerInfoService.get(this.user?.email).subscribe({
      next: (ci) => {
        this.centerInfo = ci;
        this.centerInfoCopy = Object.assign({}, this.centerInfo);
        this.formGroup1 = new FormGroup({
          name: new FormControl(this.centerInfo.name, [Validators.required]),
          description: new FormControl(this.centerInfo.description, [Validators.required]),
          rating: new FormControl(this.centerInfo.averageRating, [Validators.required, Validators.min(1), Validators.max(5)]),
          start: new FormControl(this.centerInfo.startTime, [Validators.required]),
          end: new FormControl(this.centerInfo.endTime, [Validators.required])});
          this.formGroup2 = new FormGroup({ 
          num: new FormControl(this.centerInfo.number, [Validators.required]),
          street: new FormControl(this.centerInfo.street, [Validators.required]),
          city: new FormControl(this.centerInfo.city, [Validators.required]),
          country: new FormControl(this.centerInfo.country, [Validators.required]),
        });
      },
      error: (e) => this.showError(e.message)
    });
  }

  updateCenterInfo(){
    this.centerInfoService.update(this.centerInfo).subscribe({
      next: (ci) => {
        this.showSuccess('Successfully updated blood bank center info');
        this.centerInfoCopy = ci;
      },
      error: (e) => this.showError(e.error.message)
    })
  }

  undoChanges(){
    this.centerInfo = Object.assign({}, this.centerInfoCopy);
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
