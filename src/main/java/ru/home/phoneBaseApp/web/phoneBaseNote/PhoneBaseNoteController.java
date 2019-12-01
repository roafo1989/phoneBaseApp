package ru.home.phoneBaseApp.web.phoneBaseNote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.home.phoneBaseApp.model.PhoneBaseNote;
import ru.home.phoneBaseApp.service.PhoneBaseNoteService;
import ru.home.phoneBaseApp.web.SecurityUtil;

import java.util.List;

import static ru.home.phoneBaseApp.util.ValidationUtil.assureIdConsistent;
import static ru.home.phoneBaseApp.util.ValidationUtil.checkNew;
@Controller
public class PhoneBaseNoteController {
    private static final Logger log = LoggerFactory.getLogger(PhoneBaseNoteController.class);
    private final PhoneBaseNoteService service;

    @Autowired
    public PhoneBaseNoteController(PhoneBaseNoteService service) {
        this.service = service;
    }

    public PhoneBaseNote getById(int id){
        int userId = SecurityUtil.authUserId();
        log.info("get note {} for user {}", id, userId);
        return service.getById(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete meal {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<PhoneBaseNote> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public PhoneBaseNote create(PhoneBaseNote phoneBaseNote) {
        int userId = SecurityUtil.authUserId();
        checkNew(phoneBaseNote);
        log.info("create {} for user {}", phoneBaseNote, userId);
        return service.create(phoneBaseNote, userId);
    }

    public void update(PhoneBaseNote phoneBaseNote, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(phoneBaseNote, id);
        log.info("update {} for user {}", phoneBaseNote, userId);
        service.update(phoneBaseNote, userId);
    }
    public List<PhoneBaseNote> getByNumber(long number){
        int userId = SecurityUtil.authUserId();
        log.info("get note {} for user {}", number, userId);
        return service.getByNumber(number,userId);
    }
}
