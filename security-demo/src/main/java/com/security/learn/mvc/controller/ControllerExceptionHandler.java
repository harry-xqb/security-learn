package com.security.learn.mvc.controller;

import com.security.learn.mvc.exception.UserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * create by： harry
 * date:  2019/11/24 0024 下午 2:43
 **/
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserException.class)
    @ResponseBody
    public Map<String, Object> userExceptionHandler(UserException exception){
        Map<String, Object> map = new HashMap<>();
        map.put("id", exception.getId());
        map.put("message", exception.getMessage());
        return map;
    }
}
