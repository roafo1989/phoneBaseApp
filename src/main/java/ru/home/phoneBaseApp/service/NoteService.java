package ru.home.phoneBaseApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.home.phoneBaseApp.model.Note;
import ru.home.phoneBaseApp.repository.NoteRepos;

import java.util.List;

import static ru.home.phoneBaseApp.util.ValidationUtil.checkNotFound;
import static ru.home.phoneBaseApp.util.ValidationUtil.checkNotFoundWithId;

@Service
public class NoteService {
    private final NoteRepos repos;
    @Autowired
    public NoteService(NoteRepos repos) {
        this.repos = repos;
    }

    public Note create(Note note, int userId) {
        Assert.notNull(note, "note must not be null");
        return repos.save(note, userId);
    }
    public void update(Note note, int userId) {
        Assert.notNull(note, "note must not be null");
        checkNotFoundWithId(repos.save(note, userId), note.getId());
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repos.delete(id, userId), id);
    }

    public Note getById(int id, int userId) {
        return checkNotFoundWithId(repos.getById(id, userId),id);
    }

    public List<Note> getAll(int userId) {
        return repos.getAll(userId);
    }

    public List<Note> getByNumber(long number, int userId) {
        Assert.notNull(number,"number must be not null");
        return checkNotFound(repos.getByNumber(number, userId),"number: ");
    }
    public Note getWithUser(int id, int userId){
        return checkNotFoundWithId(repos.getWithUser(id, userId), id);
    }
}
