import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { CenterInfo } from '../../../blood-bank-center-info/model/CenterInfo';
import {BloodBankCenterInfoService} from '../../../blood-bank-center-info/service/blood-bank-center-info.service';

@Component({
  selector: 'app-create-center',
  templateUrl: './create-center.component.html',
  styleUrls: ['./create-center.component.css']
})
export class CreateCenterComponent implements OnInit {
  formGroup1!: FormGroup;
  formGroup2!: FormGroup;
  centerInfo: CenterInfo = {name: '', id: 0, description: '', averageRating: 5, startTime: '', endTime: '', 
              number: '', street: '', city: '', country: ''};

  constructor(private toastr : ToastrService, private centerInfoService: BloodBankCenterInfoService) { }

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
  }
  createCenter(): void {
    this.centerInfoService.create(this.centerInfo).subscribe({
      next: (ci) => {
        this.showSuccess('Successfully created blood bank center info');
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
