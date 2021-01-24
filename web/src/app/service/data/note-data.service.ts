import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Car} from '../../components/list-cars/list-cars.component';
import {API_URL} from '../../app.constants';
import {Note} from '../../components/list-notes/list-notes.component';

@Injectable({
  providedIn: 'root'
})
export class NoteDataService {

  constructor(private http: HttpClient) { }

  // tslint:disable-next-line:typedef
  getAllNotes(){
    return this.http.get<Note[]>(`${API_URL}/notes`);
  }


  deleteNote(id){
    return this.http.delete(`${API_URL}/notes/${id}`);
  }

  getNote(id) {
    return this.http.get<Note>(`${API_URL}/notes/${id}`);
  }

  updateNote(id, car){
    return this.http.put(`${API_URL}/notes/${id}`, car);
  }

  addNote(car){
    return this.http.post(`${API_URL}/notes`, car);
  }
}
