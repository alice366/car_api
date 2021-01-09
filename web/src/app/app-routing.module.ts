import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListCarsComponent} from './components/list-cars/list-cars.component';
import {CarComponent} from './components/car/car.component';
import {ColourCarsComponent} from './components/colour-cars/colour-cars.component';
import {WelcomeComponent} from './components/welcome/welcome.component';

const routes: Routes = [
  { path: 'welcome', component: WelcomeComponent},
  { path: 'cars', component: ListCarsComponent},
  { path: 'cars/:id', component: CarComponent},
  { path: 'cars/colours/:colour', component: ColourCarsComponent},
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

})
export class AppRoutingModule { }
