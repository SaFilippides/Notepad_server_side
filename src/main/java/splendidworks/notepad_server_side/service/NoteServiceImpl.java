/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splendidworks.notepad_server_side.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import splendidworks.notepad_server_side.dao.NoteDao;
import splendidworks.notepad_server_side.model.Note;


/**
 *
 * @author filip
 */
@Service
public class NoteServiceImpl implements NoteService {

    NoteDao noteDao;

    @Autowired
    public void setAppNoteDao(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    public List<Note> listAllNotes(Integer user_id) {
        return noteDao.listAllNotes(user_id);
    }

    @Override
    public void addNote(Note note) {
        noteDao.addNote(note);
    }

    @Override
    public void updateNote(Integer user_id) {
        noteDao.updateNote(user_id);
    }

    @Override
    public void delete(Integer user_id) {
        noteDao.delete(user_id);
    }

    @Override
    public Note findNoteById(Note note) {
        return noteDao.findNoteById(note);
    }

    

}
