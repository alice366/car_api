import { Component, OnInit } from '@angular/core';
import {CurrencyGameService} from '../../service/data/currency-game.service';

@Component({
  selector: 'app-currency-game',
  templateUrl: './currency-game.component.html',
  styleUrls: ['./currency-game.component.css']
})
export class CurrencyGameComponent implements OnInit {

  currencyPLN: number;
  message: string;

  constructor(private currencyGameService: CurrencyGameService) { }

  ngOnInit(): void {
    this.currencyGameService.getExchangeRate().subscribe(
      response => {
        console.log(response);
        this.currencyPLN = response;
      });
  }

  checkValue(currency: number) {
    console.log(currency);
    this.currencyGameService.getResultOfProvidedValue(currency).subscribe(
      response => {
        console.log(response);
        this.message = response.message;
      });
  }
}
