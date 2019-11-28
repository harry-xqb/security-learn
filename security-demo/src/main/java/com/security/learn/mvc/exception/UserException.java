package com.security.learn.mvc.exception;

import lombok.Getter;

/**
 *  自定义用户异常
 * create by： harry
 * date:  2019/11/24 0024 下午 2:45
 **/
@Getter
public class UserException extends RuntimeException{

    private String id;
    private String message;

    public UserException(String message, String id) {
        super(message);
        this.id = id;
        this.message = message;
    }
}
