package com.ponder.jwtdemo.exception;

/**
 * TokenUnavailableException.java
 * 自定义JWT验证失败异常
 * @author Ponder Yao
 * @version 1.0.0  2021/7/7 14:37
 */
public class TokenUnavailableException extends TokenException {

    public TokenUnavailableException() {
        this("JWT验证失败：未知错误");
    }

    public TokenUnavailableException(String message) {
        this.code = 401;
        this.success = false;
        this.message = message;
    }

}
