package ru.home.phoneBaseApp.repository;

import ru.home.phoneBaseApp.model.Note;

import java.util.List;

public interface NoteRepos {
    Note save(Note note, int userId);
    boolean delete(int id, int userId);
    Note getById(int id, int userId);
    List<Note> getAll(int userId);
    List<Note> getByNumber(long number, int userId);
    default Note getWithUser(int id, int userId){throw new UnsupportedOperationException();}
}
