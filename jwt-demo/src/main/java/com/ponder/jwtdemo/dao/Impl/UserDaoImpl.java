package com.ponder.jwtdemo.dao.Impl;

import com.ponder.jwtdemo.dao.UserDao;
import com.ponder.jwtdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UserDaoImpl.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/7/10 17:41
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Boolean isUserExist(User user) {
        final String sql = "select id from user where username=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), user.getUsername()).size() > 0;
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        final String sql = "select * from user where username=? and password=?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username, password);
        if (users.size() > 0) return users.get(0);
        return null;
    }

}
