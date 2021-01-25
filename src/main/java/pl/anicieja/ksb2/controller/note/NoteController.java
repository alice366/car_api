package pl.anicieja.ksb2.controller.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.anicieja.ksb2.model.notes.Note;
import pl.anicieja.ksb2.repositary.NoteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/notes", produces = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NoteController {

    private NoteRepository noteRepository;

    @Autowired
    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
        createNotes();
    }

    private void createNotes() {
        Note my_note = new Note();
        my_note.setDescription("my first note");
        noteRepository.save(my_note);
        Note my_note2 = new Note();
        my_note2.setDescription("my second note");
        noteRepository.save(my_note2);
    }

    @GetMapping
    public ResponseEntity<List<Note>> getNotes() {
        List<Note> allNotes = noteRepository.findAll();
        return new ResponseEntity<>(allNotes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable long id) {
        Optional<Note> byId = noteRepository.findById(id);
        return new ResponseEntity<>(byId.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addNote(@RequestBody Note note) {
        noteRepository.save(note);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity modifyNote(@RequestBody Note newNote) {
        Optional<Note> first = noteRepository.findById(newNote.getId());

        if (first.isPresent()) {
            noteRepository.save(newNote);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeNote(@PathVariable long id) {
        Optional<Note> first = noteRepository.findById(id);

        if (first.isPresent()) {
            noteRepository.delete(first.get());
            return new ResponseEntity<>(first, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
