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
        int subscriberId = SecurityUtil.authUserId();
        log.info("get note {} for user {}", id, subscriberId);
        return service.getById(id, subscriberId);
    }

    public void delete(int id) {
        int subscriberId = SecurityUtil.authUserId();
        log.info("delete meal {} for user {}", id, subscriberId);
        service.delete(id, subscriberId);
    }

    public List<PhoneBaseNote> getAll() {
        int subscriberId = SecurityUtil.authUserId();
        log.info("getAll for user {}", subscriberId);
        return service.getAll(subscriberId);
    }

    public PhoneBaseNote create(PhoneBaseNote phoneBaseNote) {
        int subscriberId = SecurityUtil.authUserId();
        checkNew(phoneBaseNote);
        log.info("create {} for user {}", phoneBaseNote, subscriberId);
        return service.create(phoneBaseNote, subscriberId);
    }

    public void update(PhoneBaseNote phoneBaseNote, int id) {
        int subscriberId = SecurityUtil.authUserId();
        assureIdConsistent(phoneBaseNote, id);
        log.info("update {} for user {}", phoneBaseNote, subscriberId);
        service.update(phoneBaseNote, subscriberId);
    }
    public PhoneBaseNote getByNumber(long number){
        int subscriberId = SecurityUtil.authUserId();
        log.info("get note {} for user {}", number, subscriberId);
        return service.getByNumber(number,subscriberId);
    }
}
