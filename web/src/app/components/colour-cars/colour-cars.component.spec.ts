import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ColourCarsComponent } from './colour-cars.component';

describe('ColourCarsComponent', () => {
  let component: ColourCarsComponent;
  let fixture: ComponentFixture<ColourCarsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ColourCarsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ColourCarsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
