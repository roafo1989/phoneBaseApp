package ru.home.phoneBaseApp.web.phoneBaseNote;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.home.phoneBaseApp.model.Note;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/ajax/profile/meals")
public class NoteUIController extends AbstractNoteController {
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Note> getAll(){return super.getAll();}

    @Override
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("number") Long number,
                               @RequestParam("comment") String comment) {
        Note note = new Note(id,name,number,comment);

        if (note.isNew()) {
            super.create(note);
        }
    }

/*    @GetMapping(value="/filter")
    public String getByNumber(HttpServletRequest request, Model model) {
        long number = Long.parseLong(request.getParameter("number"));
        model.addAttribute("notes", super.getByNumber(number));
        return "notes";
    }*/
}
