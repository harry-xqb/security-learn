package com.security.learn.core.validate.code;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Harry Xu
 * @date 2019/11/28 17:39
 */
//@Component("imageCodeGenerator")
@Log
public class OtherImageCodeGenerator implements ValidateCodeGenerator{
    @Override
    public ImageCode generate(HttpServletRequest request) {
        log.info("覆盖的默认提供的验证码方法:" + request.getRequestURI());
        return null;
    }
}
