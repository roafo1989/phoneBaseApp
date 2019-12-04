package ru.home.phoneBaseApp.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.home.phoneBaseApp.model.Note;
import ru.home.phoneBaseApp.model.User;
import ru.home.phoneBaseApp.repository.NoteRepos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional(readOnly = true)
public class NoteReposImpl implements NoteRepos {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Note save(Note note, int userId) {
        if(!note.isNew() && getById(note.getId(),userId) == null){
            return null;
        }
        note.setUser(em.getReference(User.class,userId));
        if(note.isNew()){
            em.persist(note);
            return note;
        } else {
            return em.merge(note);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Note.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Note getById(int id, int userId) {
        Note note =em.find(Note.class,id);
        return note != null && note.getUser().getId() == userId ? note : null;
    }

    @Override
    public List<Note> getAll(int userId) {
        return em.createNamedQuery(Note.ALL_SORTED, Note.class)
                .setParameter("userId",userId)
                .getResultList();
    }

    @Override
    public List<Note> getByNumber(long number, int userId) {
/*        List<Note> notes = em.createNamedQuery(Note.BY_NUMBER, Note.class)
                .setParameter(1, number)
                .getResultList();
        return DataAccessUtils.singleResult(notes);*/

        return em.createNamedQuery(Note.BY_NUMBER, Note.class)
                .setParameter(1, number)
                .setParameter("userId",userId)
                .getResultList();
    }
}
