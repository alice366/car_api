import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Car} from 'src/app/components/list-cars/list-cars.component';
import {API_URL} from '../../app.constants';

// const httpOptions = {
//   headers: new HttpHeaders({
//     'Accept': 'application/json',
//     'Content-Type': 'application/json'
//   })
// };

@Injectable({
  providedIn: 'root'
})
export class CarDataService {

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  getAllCars(){
    return this.http.get<Car[]>(`${API_URL}/cars`);
  }


  deleteCar(id){
    return this.http.delete(`${API_URL}/cars/${id}`);
  }

  getCar(id) {
    return this.http.get<Car>(`${API_URL}/cars/${id}`);
}

  updateCar(id, car){
    return this.http.put(`${API_URL}/cars/${id}`, car);
}

  addCar(car){
    return this.http.post(`${API_URL}/cars`, car);
  }


}
