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
import splendidworks.notepad_server_side.model.AppUser;

/**
 *
 * @author filip
 */
@Repository
public class AppUserDaoImpl implements AppUserDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<AppUser> listAllUser() {

        List<AppUser> list = new ArrayList<AppUser>();

        String sql = "SELECT id, password, username FROM app_user";

        list = namedParameterJdbcTemplate.query(sql, getSqlParameterByModel(null), new AppUserMapper());

        return list;
    }

    private SqlParameterSource getSqlParameterByModel(AppUser user) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if (user != null) {
            parameterSource.addValue("id", user.getId());
            parameterSource.addValue("username", user.getUsername());
            parameterSource.addValue("password", user.getPassword());
        }
        return parameterSource;
    }

    @Override
    public List<AppUser> findUserByName(String username, String password) {

        List<AppUser> list = new ArrayList<AppUser>();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("password", password);
        
        
        String sql = "SELECT * FROM app_user WHERE username =:username AND password =:password";

        list =  namedParameterJdbcTemplate.query(sql, params, new AppUserMapper());
        return list;
        
    }

    private static final class AppUserMapper implements RowMapper<AppUser> {

        public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            AppUser user = new AppUser();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }

    }

    public boolean userExists(AppUser user) {
        String sql = "SELECT count(*) FROM app_user WHERE username =:username";
        boolean result = false;
        int count = namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterByModel(user), Integer.class);
        if (count > 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean addUser(AppUser user) {

        if (!userExists(user)) {
            String sql = "INSERT INTO app_user(username, password) VALUES(:username, :password)";
            namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updateUser(AppUser user) {
        String sql = "UPDATE app_user SET username=:username, password=:password WHERE id =:id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));
    }

    @Override
    public void delete(AppUser user) {
        String sql = "DELETE FROM app_user WHERE id=:id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));
    }

    @Override
    public AppUser findUserById(AppUser user) {
        String sql = "SELECT * FROM app_user WHERE id =:id";

        return (AppUser) namedParameterJdbcTemplate.queryForObject(sql, getSqlParameterByModel(user), new AppUserMapper()); //check
    }

}
