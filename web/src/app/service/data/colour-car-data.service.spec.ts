import { TestBed } from '@angular/core/testing';

import { ColourCarDataService } from './colour-car-data.service';

describe('ColourCarDataService', () => {
  let service: ColourCarDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ColourCarDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
