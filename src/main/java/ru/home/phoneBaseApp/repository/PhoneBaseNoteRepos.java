package ru.home.phoneBaseApp.repository;

import ru.home.phoneBaseApp.model.PhoneBaseNote;

import java.util.List;

public interface PhoneBaseNoteRepos {
    PhoneBaseNote save(PhoneBaseNote phoneBaseNote, int userId);
    boolean delete(int id, int userId);
    PhoneBaseNote getById(int id, int userId);
    List<PhoneBaseNote> getAll(int userId);
    List<PhoneBaseNote> getByNumber(long number, int userId);
}
