/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splendidworks.notepad_server_side.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;  
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import splendidworks.notepad_server_side.model.Note;
import splendidworks.notepad_server_side.service.NoteService;

/**
 *
 * @author filip
 */
@Controller
@RequestMapping("note")
public class NoteController {

    @Autowired
    NoteService noteUserService;
    

    @RequestMapping(value = "/view/", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Note>> listAllNotes() {
        List<Note> list = noteUserService.listAllNotes();

        if (list.size() == 0) {
            return new ResponseEntity<List<Note>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Note>>(list, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/add/", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody Note note) {
        noteUserService.addNote(note);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody Note note) {
        note.setId(id);
        noteUserService.addNote(note);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<Void> delete(@PathVariable("id") int id, @RequestBody Note note) {
        note.setId(id);
        noteUserService.delete(note);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
    }

}