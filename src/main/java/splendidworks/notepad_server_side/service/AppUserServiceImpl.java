/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splendidworks.notepad_server_side.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import splendidworks.notepad_server_side.model.AppUser;
import splendidworks.notepad_server_side.dao.AppUserDao;

/**
 *
 * @author filip
 */
@Service
public class AppUserServiceImpl implements AppUserService {

    AppUserDao appUserDao;

    @Autowired
    public void setAppUserDao(AppUserDao appUserDao) {
        this.appUserDao = appUserDao;
    }

    @Override
    public List listAllUser() {
        return appUserDao.listAllUser();
    }

    @Override
    public boolean addUser(AppUser user) {
        if (appUserDao.addUser(user))
        {
            return true;
        }
        return false;
    }

    @Override
    public void updateUser(AppUser user) {
        appUserDao.updateUser(user);
    }

    @Override
    public void delete(AppUser user) {
        appUserDao.delete(user);
    }

    @Override
    public AppUser findUserById(AppUser user) {
       return appUserDao.findUserById(user);
    }

    @Override
    public List<AppUser> findUserByName(String username, String password) {
       return appUserDao.findUserByName(username, password);
    }
    

}
