package com.ponder.jwtdemo.exception;

/**
 * NullTokenException.java
 * 空token异常
 * @author Ponder Yao
 * @version 1.0.0  2021/7/10 16:12
 */
public class NullTokenException extends TokenException {

    public NullTokenException() {
        this("Token为空，请登录");
    }

    public NullTokenException(String message) {
        this.code = 401;
        this.success = false;
        this.message = message;
    }

}
