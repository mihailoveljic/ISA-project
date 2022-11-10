import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodBankCenterInfoComponent } from './blood-bank-center-info.component';

describe('BloodBankCenterInfoComponent', () => {
  let component: BloodBankCenterInfoComponent;
  let fixture: ComponentFixture<BloodBankCenterInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodBankCenterInfoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodBankCenterInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
