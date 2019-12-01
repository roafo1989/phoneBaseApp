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
import ru.home.phoneBaseApp.model.PhoneBaseNote;
import ru.home.phoneBaseApp.repository.PhoneBaseNoteRepos;

import java.util.List;


@Repository
public class JdbcNoteRepository implements PhoneBaseNoteRepos {

    private static final RowMapper<PhoneBaseNote> ROW_MAPPER = BeanPropertyRowMapper.newInstance(PhoneBaseNote.class);

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
    public PhoneBaseNote save(PhoneBaseNote phoneBaseNote, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", phoneBaseNote.getId())
                .addValue("name", phoneBaseNote.getName())
                .addValue("number", phoneBaseNote.getNumber())
                .addValue("comment", phoneBaseNote.getComment())
                .addValue("user_id", userId);

        if (phoneBaseNote.isNew()) {
            Number newId = insertMeal.executeAndReturnKey(map);
            phoneBaseNote.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("" +
                            "UPDATE notes " +
                            "   SET name=:name, number=:number, comment=:comment " +
                            " WHERE id=:id AND user_id=:user_id"
                    , map) == 0) {
                return null;
            }
        }
        return phoneBaseNote;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM notes WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public PhoneBaseNote getById(int id, int userId) {
        List<PhoneBaseNote> notes = jdbcTemplate.query(
                "SELECT * FROM notes WHERE id = ? AND user_id = ?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(notes);
    }

    @Override
    public List<PhoneBaseNote> getAll(int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM notes WHERE user_id=? ORDER BY name DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<PhoneBaseNote> getByNumber(long number, int userId) {
       /* List<PhoneBaseNote> notes = jdbcTemplate.query("SELECT * FROM notes WHERE number=? AND user_id = ?", ROW_MAPPER, number, userId);
        return DataAccessUtils.singleResult(notes);*/
       return jdbcTemplate.query("SELECT * FROM notes WHERE number=? AND user_id = ?", ROW_MAPPER, number, userId);
    }
}
