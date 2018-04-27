/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splendidworks.notepad_server_side.dao;

import java.util.List;
import splendidworks.notepad_server_side.model.AppUser;

/**
 *
 * @author filip
 */
public interface AppUserDao {

    public List<AppUser> listAllUser();

    public void addUser(AppUser user);

    public void updateUser(AppUser user);

    public void delete(AppUser user);

    public AppUser findUserById(AppUser user);

}
