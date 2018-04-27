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
    public List listAllNotes() {
        return noteDao.listAllNotes();
    }

    @Override
    public void addNote(Note note) {
        noteDao.addNote(note);
    }

    @Override
    public void updateNote(Note note) {
        noteDao.updateNote(note);
    }

    @Override
    public void delete(Note note) {
        noteDao.delete(note);
    }

    @Override
    public Note findNoteById(Note note) {
        return noteDao.findNoteById(note);
    }

}
