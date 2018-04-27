/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splendidworks.notepad_server_side.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import splendidworks.notepad_server_side.model.Note;

/**
 *
 * @author filip
 */
@Repository
public class NoteDaoImpl implements NoteDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Note> listAllNotes() {
        List<Note> list = new ArrayList<Note>();

        String sql = "SELECT id, name, image_path, note FROM note";

        list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModel(null), new NoteMapper());

        return list;
    }

    private SqlParameterSource getSqlParameterByModel(Note note) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (note != null) {
            parameterSource.addValue("id", note.getId());
            parameterSource.addValue("name", note.getName());
            parameterSource.addValue("image_path", note.getImagePath());
            parameterSource.addValue("note", note.getNote());
        }
        return parameterSource;
    }

    private static final class NoteMapper implements RowMapper<Note> {

        public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
            Note note = new Note();
            note.setId(rs.getInt("id"));
            note.setName(rs.getString("name"));
            note.setImagePath(rs.getString("image_path"));
            note.setNote(rs.getString("note"));

            return note;
        }

    }

    public void addNote(Note note) {
        String sql = "INSERT INTO note(name, image_path, note) VALUES(:name, :image_path, :note)";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(note));
    }

    public void updateNote(Note note) {
        String sql = "UPDATE note SET name=:name, image_path=:image_path, note=:note WHERE id =:id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(note));
    }

    public void delete(Note note) {
        String sql = "DELETE FROM note WHERE id=:id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(note));
    }

    public Note findNoteById(Note note) {
        String sql = "SELECT * FROM note WHERE id =:id";

        return (Note) namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterByModel(note), new NoteMapper()); //check
    }

}
