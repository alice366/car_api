import { TestBed } from '@angular/core/testing';

import { YearCarDataService } from './year-car-data.service';

describe('YearCarDataService', () => {
  let service: YearCarDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(YearCarDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
