import { HasAdminOrStaffRoleGuard } from './auth/guards/has-admin-or-staff-role.guard';
import { HasStaffRoleGuard } from './auth/guards/has-staff-role.guard';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Route } from './utils/routes';
import { StaffInfoComponent } from './modules/staff-info/component/staff-info/staff-info.component';
import { BloodBankCenterInfoComponent } from './modules/blood-bank-center-info/component/blood-bank-center-info/blood-bank-center-info.component';
import { CreateStaffComponent } from './modules/staff-info/component/create-staff/create-staff.component';
import { UserReviewComponent } from './modules/user-review/user-review.component';
import { UserInfoComponent } from './modules/user-info/components/user-info/user-info.component';

import { CreateAdminComponent } from './modules/admin-info/components/create-admin/create-admin.component';
import { AppointmentComponent } from './modules/calendar/appointment/appointment.component';
import { QrReaderComponent } from './modules/user-info/components/qr-reader/qr-reader.component';
import { HasAdminRoleGuard } from './auth/guards/has-admin-role.guard';
import { HasUserRoleGuard } from './auth/guards/has-user-role.guard';

const routes: Routes = [
  { path: Route.HOME, loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule)},
  { path: Route.CALLBACK, loadChildren: () => import('./modules/callback/callback.module').then(m => m.CallbackModule)},
  { path: 'staff-info', component: StaffInfoComponent, canActivate: [HasStaffRoleGuard]},
  { path: 'appointment', component: AppointmentComponent },
  { path:'blood-bank-center-info', component: BloodBankCenterInfoComponent, canActivate: [HasStaffRoleGuard]},
  { path: Route.CREATE_STAFF, component: CreateStaffComponent, canActivate: [HasAdminRoleGuard]},
  { path: Route.CENTERS, loadChildren: () => import('./modules/centers/centers.module').then(m => m.CentersModule) },
  { path: Route.USER_REVIEW, component: UserReviewComponent, canActivate: [HasAdminOrStaffRoleGuard]},
  { path: 'questionnaire', loadChildren: () => import('./modules/questionnaire/questionnaire.module').then(m => m.QuestionnaireModule), canActivate: [HasUserRoleGuard] },
  { path: Route.USER_INFO, component: UserInfoComponent, canActivate: [HasUserRoleGuard]},
  { path: 'news', loadChildren: () => import('./modules/news/news.module').then(m => m.NewsModule) },
  { path: 'tenders', loadChildren: () => import('./modules/tenders/tenders.module').then(m => m.TendersModule), canActivate: [HasAdminRoleGuard] },
  { path: Route.CREATE_ADMIN, component: CreateAdminComponent, canActivate: [HasAdminRoleGuard]},
  { path: 'location-tracker', loadChildren: () => import('./modules/location-tracker/location-tracker.module').then(m => m.LocationTrackerModule), canActivate: [HasAdminRoleGuard]},
  { path: Route.QR_READER, component: QrReaderComponent, canActivate: [HasStaffRoleGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
