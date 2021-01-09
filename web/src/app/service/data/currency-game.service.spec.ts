import { TestBed } from '@angular/core/testing';

import { CurrencyGameService } from './currency-game.service';

describe('CurrencyGameService', () => {
  let service: CurrencyGameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CurrencyGameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
