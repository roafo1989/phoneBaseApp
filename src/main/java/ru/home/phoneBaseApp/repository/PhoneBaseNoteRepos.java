package ru.home.phoneBaseApp.repository;

import ru.home.phoneBaseApp.model.PhoneBaseNote;

import java.util.List;

public interface PhoneBaseNoteRepos {
    PhoneBaseNote save(PhoneBaseNote phoneBaseNote, int subscriberId);
    boolean delete(int id, int subscriberId);
    PhoneBaseNote getById(int id, int subscriberId);
    List<PhoneBaseNote> getAll(int subscriberId);
    PhoneBaseNote getByNumber(long number, int subscriberId);
}
