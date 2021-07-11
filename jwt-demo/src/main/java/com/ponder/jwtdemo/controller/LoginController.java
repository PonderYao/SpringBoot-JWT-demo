package com.ponder.jwtdemo.controller;

import com.ponder.jwtdemo.annotation.PassToken;
import com.ponder.jwtdemo.entity.User;
import com.ponder.jwtdemo.io.UserIO.*;
import com.ponder.jwtdemo.service.UserService;
import com.ponder.jwtdemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * LoginController.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/7/10 22:17
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PassToken
    @PostMapping(value = "/login")
    public UserLoginOutput login(UserLoginInput userLoginInput) {
        UserLoginOutput userLoginOutput = new UserLoginOutput();
        User user = userService.login(userLoginInput.getUsername(), userLoginInput.getPassword());
        if (user == null) {
            userLoginOutput.setCode(400);
            userLoginOutput.setSuccess(false);
        } else {
            userLoginOutput.setUser(user);
            String token = JwtUtil.createToken(user.getUsername(), user.getPassword(), user.getPhoneNumber());
            userLoginOutput.setToken(token);
        }
        return userLoginOutput;
    }

    @GetMapping(value = "/testToken")
    public String testToken(HttpServletRequest req) {
        return (String)req.getAttribute("phoneNumber");
    }

}
