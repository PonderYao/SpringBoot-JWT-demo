package com.ponder.jwtdemo.entity;

import lombok.Data;

import java.util.Date;

/**
 * User.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/7/10 17:40
 */
@Data
public class User {

    /**
     * 用户ID
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 注册日期
     */
    private Date registerTime;

    /**
     * 性别
     */
    private Boolean gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 专业
     */
    private String major;

    /**
     * 手机号
     */
    private String phoneNumber;

}
