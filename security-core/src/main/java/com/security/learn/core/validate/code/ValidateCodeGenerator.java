package com.security.learn.core.validate.code;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Harry Xu
 * @date 2019/11/28 17:30
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(HttpServletRequest request);
}
