package com.security.learn.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.AuthenticationException;


/**
 * @author Harry Xu
 * @date 2019/11/28 16:22
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg){
        super(msg);
    }
}
