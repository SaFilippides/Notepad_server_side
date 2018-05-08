/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splendidworks.notepad_server_side.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Note> listAllNotes(Integer user_id) {
        List<Note> list = new ArrayList<Note>();
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("user_id", user_id);

        String sql = "SELECT * FROM `note` WHERE user_id = :user_id";

        list = namedParameterJdbcTemplate.query(sql, params, new NoteMapper());

        return list;
    }

    private SqlParameterSource getSqlParameterByModel(Note note) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (note != null) {
            parameterSource.addValue("id", note.getId());
            parameterSource.addValue("name", note.getName());
            parameterSource.addValue("image_path", note.getImagePath());
            parameterSource.addValue("note", note.getNote());
            parameterSource.addValue("user_id", note.getUser_id());
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
            note.setUser_id(rs.getInt("user_id"));

            return note;
        }

    }

    @Override
    public void addNote(Note note) {

        String sql = "INSERT INTO note(name, note, image_path, user_id) VALUES(:name, :note, :image_path, :user_id)";
        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(note));

    }

    @Override
    public void updateNote(Note note) {
        String sql = "UPDATE note SET name=:name, image_path=:image_path, note=:note WHERE id =:id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(note));
    }

    @Override
    public void delete(Integer id) {
        
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        String sql = "DELETE FROM note WHERE id=:id";

        namedParameterJdbcTemplate.update(sql, parameterSource);
    }

    public Note findNoteById(Note note) {
        String sql = "SELECT * FROM note WHERE id =:id";

        return (Note) namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterByModel(note), new NoteMapper()); //check
    }

}
