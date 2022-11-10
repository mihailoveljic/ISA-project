import { Component, OnInit } from '@angular/core';
//import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-create-center',
  templateUrl: './create-center.component.html',
  styleUrls: ['./create-center.component.css']
})
export class CreateCenterComponent implements OnInit {
  //formGroup!: FormGroup;

  constructor(private toastr : ToastrService) { }

  ngOnInit(): void {
  }
  createCenter(): void {
    
  }

}
