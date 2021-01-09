import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Car} from '../../components/list-cars/list-cars.component';
import {API_URL} from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class ColourCarDataService {
  constructor(private http: HttpClient) { }

  getCarsByColour(colour){
    return this.http.get<Car[]>(`${API_URL}/cars/colours/${colour}`);
  }
}
