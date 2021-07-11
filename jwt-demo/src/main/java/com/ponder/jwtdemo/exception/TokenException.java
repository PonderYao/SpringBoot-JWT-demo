package com.ponder.jwtdemo.exception;

/**
 * TokenException.java
 *
 * @author Ponder Yao
 * @version 1.0.0  2021/7/10 16:13
 */
public class TokenException extends Exception {

    /**
     * 状态码
     */
    protected Integer code;
    /**
     * 成功状态
     */
    protected Boolean success;
    /**
     * 异常信息
     */
    protected String message;

    public TokenException() {
        this.code = 401;
        this.success = false;
        this.message = "";
    }

}
