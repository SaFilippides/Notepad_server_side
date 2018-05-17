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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;

import splendidworks.notepad_server_side.model.AppUser;
import splendidworks.notepad_server_side.service.AppUserService;

/**
 *
 * @author filip
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    AppUserService appUserService;

    @RequestMapping(value = "/view/", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<AppUser>> listAllUser() {
        List<AppUser> list = appUserService.listAllUser();

        if (list.size() == 0) {
            return new ResponseEntity<List<AppUser>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<AppUser>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/checkbyyname/{username}/{password}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<List<AppUser>> checkByName(@PathVariable("username") String username, @PathVariable("password") String password) {
        List<AppUser> list = appUserService.findUserByName(username, password);
        HttpHeaders headers = new HttpHeaders();
        
        if (list.size() == 0) {
            return new ResponseEntity<List<AppUser>>(headers, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<List<AppUser>>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/add/", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<Void> add(@RequestBody AppUser user) {
        boolean check;

        check = appUserService.addUser(user);
        
        HttpHeaders headers = new HttpHeaders();
        if (check)
        {
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }
        return new ResponseEntity<Void>(headers, HttpStatus.NOT_ACCEPTABLE);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<Void> update(@PathVariable("id") int id, @RequestBody AppUser user) {
        user.setId(id);
        appUserService.updateUser(user);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<Void> delete(@PathVariable("id") int id, @RequestBody AppUser user) {
        user.setId(id);
        appUserService.delete(user);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.NO_CONTENT);
    }

}
