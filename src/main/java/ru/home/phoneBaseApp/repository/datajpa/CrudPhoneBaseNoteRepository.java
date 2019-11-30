package ru.home.phoneBaseApp.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.home.phoneBaseApp.model.PhoneBaseNote;

import java.util.List;

public interface CrudPhoneBaseNoteRepository extends JpaRepository<PhoneBaseNote, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM PhoneBaseNote p WHERE p.id=:id AND p.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    PhoneBaseNote save(PhoneBaseNote item);

    @Query("SELECT p FROM PhoneBaseNote p WHERE p.user.id=:userId ORDER BY p.name DESC")
    List<PhoneBaseNote> getAll(@Param("userId") int userId);

    @Query("SELECT p from PhoneBaseNote p WHERE p.user.id=:userId AND p.number=:number ORDER BY p.name DESC")
    PhoneBaseNote getByNumber(@Param("number") Long number, @Param("userId") int userId);
}
