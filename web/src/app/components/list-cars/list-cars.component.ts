import { Component, OnInit } from '@angular/core';
import { CarDataService } from '../../service/data/car-data.service';
import { Router } from '@angular/router';
import {ColourCarDataService} from '../../service/data/colour-car-data.service';
import {YearCarDataService} from '../../service/data/year-car-data.service';

export class Car {
  constructor(
    public id: number,
    public brand: string,
    public model: string,
    public colour: string,
    public year: number,
  ){}
}

@Component({
  selector: 'app-list-cars',
  templateUrl: './list-cars.component.html',
  styleUrls: ['./list-cars.component.css']
})
export class ListCarsComponent implements OnInit {

  cars: Car[];
  message: string;
  colourCars: Car[];
  yearCars: Car[];
  yearStart: number;
  yearEnd: number;

  constructor(
    private carDataService: CarDataService,
    private colourCarDataService: ColourCarDataService,
    private yearCarDataService: YearCarDataService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.refreshCars();
  }

  // tslint:disable-next-line:typedef
  refreshCars() {
    this.carDataService.getAllCars().subscribe(
      response => {
        console.log(response);
        console.log(response.length);
        this.cars = response;
      }
    );
  }

  // tslint:disable-next-line:typedef
  updateCar(id: number) {
    this.router.navigate(['cars', id]);
    console.log(id);
  }

  deleteCar(id: number) {
    this.carDataService.deleteCar(id).subscribe(
      response => {
        console.log(response);
        this.message = `Delete of Car ${id} successfull!`;
        this.refreshCars();
      });
  }

  createCar() {
    console.log("create");
    this.router.navigate(['cars', -1]);
  }

  uploadColourOfCar(event: any) {
  this.colourCarDataService.getCarsByColour(event.target.value).subscribe(
    response => {
      console.log(response);
      this.colourCars = response;
      this.router.navigate(['cars/colours', event.target.value]);
    });
  }

  uploadYearStart(event: any){
    this.yearStart = event.target.value;
  }

  uploadYearEnd(event: any){
    this.yearEnd = event.target.value;
  }

  uploadYearOfCars() {
    this.yearCarDataService.getCarsByYear(this.yearStart, this.yearEnd).subscribe(
      response => {
        console.log(response);
        this.yearCars = response;
        this.router.navigate(['cars/year', this.yearStart, this.yearEnd]);
      }
    );
  }
}
