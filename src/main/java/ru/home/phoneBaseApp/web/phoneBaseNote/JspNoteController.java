package ru.home.phoneBaseApp.web.phoneBaseNote;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.home.phoneBaseApp.model.Note;
import ru.home.phoneBaseApp.service.NoteService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(value = "/notes")
public class JspNoteController extends AbstractNoteController {

    public int getId(HttpServletRequest request){
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/notes";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("note",super.getById(getId(request)));
        return "noteForm";
    }

    @GetMapping("/getAll")
    public List<Note> getAll() {
        return super.getAll();
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("note", new Note());
        return "noteForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request){
        Note note = new Note(
                request.getParameter("name"),
                Long.parseLong(request.getParameter("number")),
                request.getParameter("comment"));
        if(request.getParameter("id").isEmpty()){
            super.create(note);
        } else {
            super.update(note,getId(request));
        }
        return "redirect:/notes";
    }


    @GetMapping("/filter")
    public String getByNumber(HttpServletRequest request, Model model) {
        long number = Long.parseLong(request.getParameter("number"));
        model.addAttribute("notes", super.getByNumber(number));
        return "notes";
    }

    @GetMapping("/getById")
    public Note getById(int id) {
        return super.getById(id);
    }
}
