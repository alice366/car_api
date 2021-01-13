import { Component, OnInit } from '@angular/core';
import {Car} from '../list-cars/list-cars.component';
import {ColourCarDataService} from '../../service/data/colour-car-data.service';
import {ActivatedRoute} from '@angular/router';
import {YearCarDataService} from '../../service/data/year-car-data.service';

@Component({
  selector: 'app-year-cars',
  templateUrl: './year-cars.component.html',
  styleUrls: ['./year-cars.component.css']
})
export class YearCarsComponent implements OnInit {

  yearCars: Car[];

  constructor(
    private yearCarDataService: YearCarDataService,
    private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    console.log(this.route.snapshot.params['yearStart']);
    console.log(this.route.snapshot.params['yearEnd']);
    this.yearCarDataService.getCarsByYear(this.route.snapshot.params['yearStart'],this.route.snapshot.params['yearEnd'] ).subscribe(
      response => {
        console.log(response);
        this.yearCars = response;
        console.log(this.yearCars.length);
      });
  }

}
