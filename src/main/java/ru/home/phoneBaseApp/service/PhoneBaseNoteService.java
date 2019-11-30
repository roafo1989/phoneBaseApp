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

    public PhoneBaseNote create(PhoneBaseNote phoneBaseNote, int userId) {
        Assert.notNull(phoneBaseNote, "note must not be null");
        return repos.save(phoneBaseNote, userId);
    }
    public void update(PhoneBaseNote phoneBaseNote, int userId) {
        Assert.notNull(phoneBaseNote, "note must not be null");
        checkNotFoundWithId(repos.save(phoneBaseNote, userId),phoneBaseNote.getId());
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repos.delete(id, userId), id);
    }

    public PhoneBaseNote getById(int id, int userId) {
        return checkNotFoundWithId(repos.getById(id, userId),id);
    }

    public List<PhoneBaseNote> getAll(int userId) {
        return repos.getAll(userId);
    }

    public PhoneBaseNote getByNumber(long number, int userId) {
        Assert.notNull(number,"number must be not null");
        return checkNotFound(repos.getByNumber(number, userId),"number: ");
    }
}
