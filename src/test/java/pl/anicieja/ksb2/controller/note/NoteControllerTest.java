package pl.anicieja.ksb2.controller.note;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.anicieja.ksb2.model.notes.Note;
import com.google.gson.Gson;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetNotes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/notes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description", Is.is("my first note")));
    }

    @Test
    void shouldGetNoteById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/notes/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("description", Is.is("my first note")));
    }

    @Test
    void shouldAddNote() throws Exception {
        Note note = new Note();
        note.setDescription("my third note");

        Gson gson = new Gson();
        String json = gson.toJson(note);

        mockMvc.perform(MockMvcRequestBuilders.post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldModifyNote() throws Exception {
        Note note = new Note(3, "my third note modified");

        Gson gson = new Gson();
        String json = gson.toJson(note);

        mockMvc.perform(MockMvcRequestBuilders.put("/notes/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());

    }

    @Test
    void shouldRemoveNote() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/notes/{id}", 2))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/notes"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
    }
}
