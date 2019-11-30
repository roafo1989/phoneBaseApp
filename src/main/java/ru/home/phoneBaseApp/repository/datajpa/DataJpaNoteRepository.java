package ru.home.phoneBaseApp.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.home.phoneBaseApp.model.PhoneBaseNote;
import ru.home.phoneBaseApp.repository.PhoneBaseNoteRepos;

import java.util.List;

@Repository
public class DataJpaNoteRepository implements PhoneBaseNoteRepos {
    @Autowired
    private CrudUserRepository crudUserRepository;
    @Autowired
    private CrudPhoneBaseNoteRepository crudPhoneBaseNoteRepository;


    @Override
    public PhoneBaseNote save(PhoneBaseNote phoneBaseNote, int userId) {
        if(!phoneBaseNote.isNew() && getById(phoneBaseNote.getId(),userId) == null){
            return null;
        }
        phoneBaseNote.setUser(crudUserRepository.getOne(userId));
        return crudPhoneBaseNoteRepository.save(phoneBaseNote);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudPhoneBaseNoteRepository.delete(id,userId) != 0;
    }

    @Override
    public PhoneBaseNote getById(int id, int userId) {
        return crudPhoneBaseNoteRepository.findById(id).filter(phoneBaseNote -> phoneBaseNote.getUser().getId() == userId).orElse(null);
    }

    @Override
    public List<PhoneBaseNote> getAll(int userId) {
        return crudPhoneBaseNoteRepository.getAll(userId);
    }

    @Override
    public PhoneBaseNote getByNumber(long number, int userId) {
        return crudPhoneBaseNoteRepository.getByNumber(number,userId);
    }
}
