import { Component, OnInit } from '@angular/core';

import {CarDataService} from '../../service/data/car-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Car} from '../list-cars/list-cars.component';


@Component({
  selector: 'app-car',
  templateUrl: './car.component.html',
  styleUrls: ['./car.component.css']
})
export class CarComponent implements OnInit {

  id: number;
  // tslint:disable-next-line:ban-types
  car: Car;

  constructor(
    private carService: CarDataService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.car = new Car(this.id, '', '', '', 0);
    // tslint:disable-next-line:triple-equals
    if (this.id != -1) {
        this.carService.getCar(this.id).subscribe(
          data => this.car = data
        );
    }
  }

  // tslint:disable-next-line:typedef
  saveCar() {
    console.log("save");

    // tslint:disable-next-line:triple-equals
    if (this.id == -1) {
      this.carService.addCar(this.car).subscribe(
        data => {
          console.log(data);
          this.router.navigate(['cars']);
        });
    } else {
      this.carService.updateCar(this.id, this.car).subscribe(data => {
        console.log(data);
        this.router.navigate(['cars']);
      });
    }
  }
}
