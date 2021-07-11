package com.ponder.jwtdemo.io;

import com.ponder.jwtdemo.entity.User;
import lombok.Data;

/**
 * UserIO.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/7/10 22:22
 */
public interface UserIO {

    @Data
    public class UserLoginOutput {

        private Integer code = 200;

        private Boolean success = true;

        private User user;

        private String token = "";

    }

    @Data
    public class UserLoginInput {

        private String username;

        private String password;

    }

}
