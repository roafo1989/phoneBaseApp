package ru.home.phoneBaseApp.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.home.phoneBaseApp.model.Note;
import ru.home.phoneBaseApp.repository.NoteRepos;

import java.util.List;

@Repository
public class DataJpaNoteRepository implements NoteRepos {
    @Autowired
    private CrudUserRepository crudUserRepository;
    @Autowired
    private CrudNoteRepository crudNoteRepository;


    @Override
    public Note save(Note note, int userId) {
        if(!note.isNew() && getById(note.getId(),userId) == null){
            return null;
        }
        note.setUser(crudUserRepository.getOne(userId));
        return crudNoteRepository.save(note);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudNoteRepository.delete(id,userId) != 0;
    }

    @Override
    public Note getById(int id, int userId) {
        return crudNoteRepository.findById(id).filter(phoneBaseNote -> phoneBaseNote.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<Note> getAll(int userId) {
        return crudNoteRepository.getAll(userId);
    }

    @Override
    public List<Note> getByNumber(long number, int userId) {
        return crudNoteRepository.getByNumber(number,userId);
    }

    @Override
    public Note getWithUser(int id, int userId) {
        return crudNoteRepository.getWithUser(id,userId);
    }
}
