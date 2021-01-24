import { Component, OnInit } from '@angular/core';
import {NoteDataService} from '../../service/data/note-data.service';
import {Router} from '@angular/router';

export class Note {
  constructor(
    public id: number,
    public description: string,
  ){}
}

@Component({
  selector: 'app-list-notes',
  templateUrl: './list-notes.component.html',
  styleUrls: ['./list-notes.component.css']
})
export class ListNotesComponent implements OnInit {

  notes: Note[];
  message: string;

  constructor(private noteDataService: NoteDataService,
              private router: Router) { }

  ngOnInit(): void {
    this.refreshNotes();
  }

  // tslint:disable-next-line:typedef
  refreshNotes() {
    this.noteDataService.getAllNotes().subscribe(
      response => {
        console.log(response);
        console.log(response.length);
        this.notes = response;
      }
    );
  }

  updateNote(id: number) {
    this.router.navigate(['notes', id]);
    console.log(id);
  }

  deleteNote(id: number) {
    this.noteDataService.deleteNote(id).subscribe(
      response => {
        console.log(response);
        this.message = `Delete of Car ${id} successfull!`;
        this.refreshNotes();
      });
  }

  createNote() {
    console.log("create");
    this.router.navigate(['notes', -1]);
  }

}
