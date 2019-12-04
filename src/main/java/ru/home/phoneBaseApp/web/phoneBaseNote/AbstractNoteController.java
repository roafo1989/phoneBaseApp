package ru.home.phoneBaseApp.web.phoneBaseNote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.home.phoneBaseApp.model.Note;
import ru.home.phoneBaseApp.service.NoteService;
import ru.home.phoneBaseApp.web.SecurityUtil;

import java.util.List;

import static ru.home.phoneBaseApp.util.ValidationUtil.assureIdConsistent;
import static ru.home.phoneBaseApp.util.ValidationUtil.checkNew;

public abstract class AbstractNoteController {
    private static final Logger log = LoggerFactory.getLogger(AbstractNoteController.class);

    @Autowired
    private NoteService service;


    public Note getById(int id){
        int userId = SecurityUtil.authUserId();
        log.info("get note {} for user {}", id, userId);
        return service.getById(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete meal {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Note> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public Note create(Note note) {
        note.setId(null);
        int userId = SecurityUtil.authUserId();
        checkNew(note);
        log.info("create {} for user {}", note, userId);
        return service.create(note, userId);
    }

    public void update(Note note, int id) {
        note.setId(id);
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(note, id);
        log.info("update {} for user {}", note, userId);
        service.update(note, userId);
    }
    public List<Note> getByNumber(long number){
        int userId = SecurityUtil.authUserId();
        log.info("get note {} for user {}", number, userId);
        return service.getByNumber(number,userId);
    }
}
