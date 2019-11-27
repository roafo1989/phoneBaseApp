package ru.home.phoneBaseApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.home.phoneBaseApp.model.PhoneBaseNote;
import ru.home.phoneBaseApp.repository.PhoneBaseNoteRepos;

import java.util.List;

import static ru.home.phoneBaseApp.util.ValidationUtil.checkNotFound;
import static ru.home.phoneBaseApp.util.ValidationUtil.checkNotFoundWithId;

@Service
public class PhoneBaseNoteService{
    private final PhoneBaseNoteRepos repos;
    @Autowired
    public PhoneBaseNoteService(PhoneBaseNoteRepos repos) {
        this.repos = repos;
    }

    public PhoneBaseNote create(PhoneBaseNote phoneBaseNote, int subscriberId) {
        Assert.notNull(phoneBaseNote, "note must not be null");
        return repos.save(phoneBaseNote,subscriberId);
    }
    public void update(PhoneBaseNote phoneBaseNote, int subscriberId) {
        Assert.notNull(phoneBaseNote, "note must not be null");
        checkNotFoundWithId(repos.save(phoneBaseNote,subscriberId),phoneBaseNote.getId());
    }

    public void delete(int id, int subscriberId) {
        checkNotFoundWithId(repos.delete(id,subscriberId), id);
    }

    public PhoneBaseNote getById(int id, int subscriberId) {
        return checkNotFoundWithId(repos.getById(id,subscriberId),id);
    }

    public List<PhoneBaseNote> getAll(int subscriberId) {
        return repos.getAll(subscriberId);
    }

    public PhoneBaseNote getByNumber(long number, int subscriberId) {
        Assert.notNull(number,"number must be not null");
        return checkNotFound(repos.getByNumber(number,subscriberId),"number: ");
    }
}
