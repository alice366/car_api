import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Car} from '../../components/list-cars/list-cars.component';
import {API_URL} from '../../app.constants';

@Injectable({
  providedIn: 'root'
})
export class YearCarDataService {

  constructor(private http: HttpClient) { }

  getCarsByYear(yearStart, yearEnd){
    return this.http.get<Car[]>(`${API_URL}/cars/year/${yearStart}/${yearEnd}`);
  }
}
