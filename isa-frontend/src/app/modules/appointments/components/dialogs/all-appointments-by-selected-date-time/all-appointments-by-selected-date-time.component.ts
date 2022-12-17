import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { ToastrService } from 'ngx-toastr';
import { AppointmentService } from '../../../services/appointment.service';

@Component({
  selector: 'app-all-appointments-by-selected-date-time',
  templateUrl: './all-appointments-by-selected-date-time.component.html',
  styleUrls: ['./all-appointments-by-selected-date-time.component.css']
})
export class AllAppointmentsBySelectedDateTimeComponent implements OnInit {

  allAppointmentsBySelectedDateTime: any;
  displayedColumns: string[] = [
    'date',
    'duration',
    'description',
    'price',
    'bloodBankForAppointment',
    'staff',
    'buttons',
  ];
  dataSource = new MatTableDataSource<any>();
  constructor(
    public dialogRef: MatDialogRef<AllAppointmentsBySelectedDateTimeComponent>,
    public dialog: MatDialog,
    private appointmentService: AppointmentService,
    private toastrService: ToastrService,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource(this.data);
  }
  closeDialog(appointment: any): any {
    this.appointmentService.scheduleAppointment(appointment).subscribe({
      next: (result:any) => {
        if(result){
          this.showSuccess();
        }
      },
      error: (e:any) => {
        this.toastrService.error(e.message);
      }
    });
    this.dialogRef.close();
  }
  showSuccess() {
    this.toastrService.success(
      'Successfully scheduled appointment!',
      'Blood bank application'
    );
  }
}
