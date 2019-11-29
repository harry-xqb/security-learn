package com.security.learn.core.exception;

/**
 * @author Harry Xu
 * @date 2019/11/29 14:45
 */
public class SmsException extends RuntimeException {

    private String msg;

    public SmsException(String msg){
        super(msg);
    }
}
