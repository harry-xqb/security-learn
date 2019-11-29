package com.security.learn.core.hanlder;

import com.security.learn.core.exception.SmsException;
import org.omg.CORBA.UserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Harry Xu
 * @date 2019/11/29 14:48
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SmsException.class)
    @ResponseBody
    public Map<String, Object> userExceptionHandler(SmsException smsException){
        Map<String, Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("message", smsException.getMessage());
        return map;
    }
}
