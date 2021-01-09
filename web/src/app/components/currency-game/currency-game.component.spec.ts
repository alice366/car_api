import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrencyGameComponent } from './currency-game.component';

describe('CurrencyGameComponent', () => {
  let component: CurrencyGameComponent;
  let fixture: ComponentFixture<CurrencyGameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrencyGameComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CurrencyGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
