package ru.home.phoneBaseApp.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.home.phoneBaseApp.model.Note;

import java.util.List;

public interface CrudNoteRepository extends JpaRepository<Note, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Note p WHERE p.id=:id AND p.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Note save(Note item);

    @Query("SELECT p FROM Note p WHERE p.user.id=:userId ORDER BY p.name DESC")
    List<Note> getAll(@Param("userId") int userId);

    @Query("SELECT p from Note p WHERE p.user.id=:userId AND p.number=:number ORDER BY p.name DESC")
    List<Note> getByNumber(@Param("number") Long number, @Param("userId") int userId);

    @Query("SELECT p from Note p JOIN FETCH p.user WHERE p.id = ?1 AND p.user.id = ?2")
    Note getWithUser(int id, int userId);
}
