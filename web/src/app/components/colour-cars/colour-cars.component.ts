import { Component, OnInit } from '@angular/core';
import {Car} from '../list-cars/list-cars.component';
import {ActivatedRoute, Router} from '@angular/router';
import {ColourCarDataService} from '../../service/data/colour-car-data.service';

@Component({
  selector: 'app-colour-cars',
  templateUrl: './colour-cars.component.html',
  styleUrls: ['./colour-cars.component.css']
})
export class ColourCarsComponent implements OnInit {

  colourCars: Car[];

  constructor(
    private colourCarDataService: ColourCarDataService,
    private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    console.log(this.route.snapshot.params['colour']);
    this.colourCarDataService.getCarsByColour(this.route.snapshot.params['colour']).subscribe(
      response => {
        console.log(response);
        this.colourCars = response;
        console.log(this.colourCars.length);
      });
  }
}
