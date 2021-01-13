import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YearCarsComponent } from './year-cars.component';

describe('YearCarsComponent', () => {
  let component: YearCarsComponent;
  let fixture: ComponentFixture<YearCarsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YearCarsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(YearCarsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
