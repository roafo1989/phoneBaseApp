package ru.home.phoneBaseApp.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.home.phoneBaseApp.model.Note;
import ru.home.phoneBaseApp.repository.NoteRepos;

import java.util.List;


@Repository
public class JdbcNoteRepository implements NoteRepos {

    private static final RowMapper<Note> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Note.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcNoteRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMeal = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("notess")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Note save(Note note, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", note.getId())
                .addValue("name", note.getName())
                .addValue("number", note.getNumber())
                .addValue("comment", note.getComment())
                .addValue("user_id", userId);

        if (note.isNew()) {
            Number newId = insertMeal.executeAndReturnKey(map);
            note.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE notes " +
                            "   SET name=:name, number=:number, comment=:comment " +
                            " WHERE id=:id AND user_id=:user_id"
                    , map) == 0) {
                return null;
            }
        }
        return note;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM notes WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Note getById(int id, int userId) {
        List<Note> notes = jdbcTemplate.query(
                "SELECT * FROM notes WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(notes);
    }

    @Override
    public List<Note> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM notes WHERE user_id=? ORDER BY name DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<Note> getByNumber(long number, int userId) {
       /* List<Note> notes = jdbcTemplate.query("SELECT * FROM notes WHERE number=? AND user_id = ?", ROW_MAPPER, number, userId);
        return DataAccessUtils.singleResult(notes);*/
       return jdbcTemplate.query("SELECT * FROM notes WHERE number=? AND user_id = ?", ROW_MAPPER, number, userId);
    }
}
