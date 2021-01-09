import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {API_URL} from '../../app.constants';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class CurrencyGameService {

  constructor(private http: HttpClient) { }

  getExchangeRate(){
    return this.http.get<number>(`${API_URL}/currency-game-java`);
  }

  getResultOfProvidedValue(currency: number){
    return this.http.post<Message>(`${API_URL}/currency-game-java`, currency, httpOptions);
  }
}

export interface Message {
  message: string;
}
