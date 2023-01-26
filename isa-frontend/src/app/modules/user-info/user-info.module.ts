import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../angular-material/angular-material.module';
import { UserInfoComponent } from './components/user-info/user-info.component';
import { NavbarModule } from '../navbar/navbar.module';
import { ReactiveFormsModule } from '@angular/forms';
import { QrReaderComponent } from './components/qr-reader/qr-reader.component';
import { NgxQRCodeModule } from 'ngx-qrcode2';

@NgModule({
  declarations: [UserInfoComponent, QrReaderComponent],
  imports: [
    CommonModule,
    MaterialModule,
    NavbarModule,
    ReactiveFormsModule,
    NgxQRCodeModule,
  ]
})
export class UserInfoModule { }
