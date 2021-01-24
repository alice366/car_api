import { Component, OnInit } from '@angular/core';
import {Car} from '../list-cars/list-cars.component';
import {CarDataService} from '../../service/data/car-data.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Note} from '../list-notes/list-notes.component';
import {NoteDataService} from '../../service/data/note-data.service';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.css']
})
export class NoteComponent implements OnInit {

  id: number;
  // tslint:disable-next-line:ban-types
  note: Note;

  constructor(
    private noteService: NoteDataService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.note = new Note(this.id, '');
    // tslint:disable-next-line:triple-equals
    if (this.id != -1) {
      this.noteService.getNote(this.id).subscribe(
        data => this.note = data
      );
    }
  }

  // tslint:disable-next-line:typedef
  saveNote() {
    console.log("save");

    // tslint:disable-next-line:triple-equals
    if (this.id == -1) {
      this.noteService.addNote(this.note).subscribe(
        data => {
          console.log(data);
          this.router.navigate(['notes']);
        });
    } else {
      this.noteService.updateNote(this.id, this.note).subscribe(data => {
        console.log(data);
        this.router.navigate(['notes']);
      });
    }
  }

}
