package com.ponder.jwtdemo.dao;

import com.ponder.jwtdemo.entity.User;
import org.springframework.stereotype.Component;

/**
 * UserDao.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/7/10 17:41
 */
public interface UserDao {

    /**
     * 验证用户是否存在
     * @param user 用户
     * @return 布尔值
     */
    public Boolean isUserExist(User user);

    /**
     * 根据账号密码查找用户信息
     * @param username 姓名
     * @param password 密码
     * @return {@link com.ponder.jwtdemo.entity.User}
     */
    public User findUserByUsernameAndPassword(String username, String password);

}
