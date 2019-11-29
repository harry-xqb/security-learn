package com.security.learn.core.validate.code;

import com.security.learn.core.property.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Harry Xu
 * @date 2019/11/29 10:55
 */
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(HttpServletRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        System.out.println("code :" + code);
        int expireIn = securityProperties.getCode().getSms().getExpireIn();
        ValidateCode validateCode = new ValidateCode(code, expireIn);
        return validateCode;
    }
}
