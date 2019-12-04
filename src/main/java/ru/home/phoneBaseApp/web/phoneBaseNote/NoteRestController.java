package ru.home.phoneBaseApp.web.phoneBaseNote;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.home.phoneBaseApp.model.Note;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = NoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class NoteRestController extends AbstractNoteController {

    static final String REST_URL = "/rest/profile/meals";

    @Override
    @GetMapping("/{id}")
    public Note getById(@PathVariable int id) {
        return super.getById(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);

    }

    @Override
    @GetMapping
    public List<Note> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Note note,@PathVariable int id) {
        super.update(note, id);
    }

    @Override
    @GetMapping("/by")
    public List<Note> getByNumber(@RequestParam long number) {
        return super.getByNumber(number);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> createWithLocation(@RequestBody Note note) {
        Note created = super.create(note);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
