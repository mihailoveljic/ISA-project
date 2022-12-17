import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllAppointmentsBySelectedDateTimeComponent } from './all-appointments-by-selected-date-time.component';

describe('AllAppointmentsBySelectedDateTimeComponent', () => {
  let component: AllAppointmentsBySelectedDateTimeComponent;
  let fixture: ComponentFixture<AllAppointmentsBySelectedDateTimeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllAppointmentsBySelectedDateTimeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllAppointmentsBySelectedDateTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
