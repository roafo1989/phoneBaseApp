package ru.home.phoneBaseApp.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.home.phoneBaseApp.model.PhoneBaseNote;
import ru.home.phoneBaseApp.model.User;
import ru.home.phoneBaseApp.repository.PhoneBaseNoteRepos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional(readOnly = true)
public class PhoneBaseNoteReposImpl implements PhoneBaseNoteRepos {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public PhoneBaseNote save(PhoneBaseNote phoneBaseNote, int userId) {
        if(!phoneBaseNote.isNew() && getById(phoneBaseNote.getId(),userId) == null){
            return null;
        }
        phoneBaseNote.setUser(em.getReference(User.class,userId));
        if(phoneBaseNote.isNew()){
            em.persist(phoneBaseNote);
            return phoneBaseNote;
        } else {
            return em.merge(phoneBaseNote);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(PhoneBaseNote.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public PhoneBaseNote getById(int id, int userId) {
        PhoneBaseNote phoneBaseNote =em.find(PhoneBaseNote.class,id);
        return phoneBaseNote != null && phoneBaseNote.getUser().getId() == userId ? phoneBaseNote : null;
    }

    @Override
    public List<PhoneBaseNote> getAll(int userId) {
        return em.createNamedQuery(PhoneBaseNote.ALL_SORTED,PhoneBaseNote.class)
                .setParameter("userId",userId)
                .getResultList();
    }

    @Override
    public PhoneBaseNote getByNumber(long number, int userId) {
        List<PhoneBaseNote> notes = em.createNamedQuery(PhoneBaseNote.BY_NUMBER, PhoneBaseNote.class)
                .setParameter(1, number)
                .getResultList();
        return DataAccessUtils.singleResult(notes);
    }
}
