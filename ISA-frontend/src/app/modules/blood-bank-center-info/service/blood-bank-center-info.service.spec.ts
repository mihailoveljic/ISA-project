import { TestBed } from '@angular/core/testing';

import { BloodBankCenterInfoService } from './blood-bank-center-info.service';

describe('BloodBankCenterInfoService', () => {
  let service: BloodBankCenterInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BloodBankCenterInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
