import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListCarsComponent} from './components/list-cars/list-cars.component';
import {CarComponent} from './components/car/car.component';
import {ColourCarsComponent} from './components/colour-cars/colour-cars.component';
import {WelcomeComponent} from './components/welcome/welcome.component';
import {CurrencyGameComponent} from './components/currency-game/currency-game.component';
import {YearCarsComponent} from './components/year-cars/year-cars.component';
import {ListNotesComponent} from './components/list-notes/list-notes.component';
import {NoteComponent} from './components/note/note.component';

const routes: Routes = [
  { path: 'welcome', component: WelcomeComponent},
  { path: 'cars', component: ListCarsComponent},
  { path: 'notes', component: ListNotesComponent},
  { path: 'cars/:id', component: CarComponent},
  { path: 'notes/:id', component: NoteComponent},
  { path: 'cars/colours/:colour', component: ColourCarsComponent},
  { path: 'cars/year/:yearStart/:yearEnd', component: YearCarsComponent},
  { path: 'currency-game-java', component: CurrencyGameComponent},
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]

})
export class AppRoutingModule { }
