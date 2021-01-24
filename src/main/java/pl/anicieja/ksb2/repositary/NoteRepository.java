package pl.anicieja.ksb2.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.anicieja.ksb2.model.notes.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
