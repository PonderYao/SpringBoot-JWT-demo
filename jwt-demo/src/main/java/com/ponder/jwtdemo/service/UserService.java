package com.ponder.jwtdemo.service;

import com.ponder.jwtdemo.dao.UserDao;
import com.ponder.jwtdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/7/10 18:25
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public boolean checkUser(String username) {
        User user = new User();
        user.setUsername(username);
        return userDao.isUserExist(user);
    }

    public User login(String username, String password) {
        return userDao.findUserByUsernameAndPassword(username, password);
    }

}
