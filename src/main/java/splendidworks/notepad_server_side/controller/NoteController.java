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

    @RequestMapping(value = "/view/{user_id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<Note>> listAllNotes(@PathVariable("user_id") Integer user_id) {
        List<Note> list = noteUserService.listAllNotes(user_id);

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

    @RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<Void> update(@RequestBody Note note) {

        noteUserService.updateNote(note);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {

        noteUserService.delete(id);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
    }

}
