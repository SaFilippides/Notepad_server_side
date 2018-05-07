/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splendidworks.notepad_server_side.service;

import java.util.List;

import splendidworks.notepad_server_side.model.Note;

/**
 *
 * @author filip
 */
public interface NoteService {
    
    
    public List<Note> listAllNotes(Integer user_id);

    public void addNote(Note note);

    public void updateNote(Integer user_id);

    public void delete(Integer user_id);

    public Note findNoteById(Note note);

}
