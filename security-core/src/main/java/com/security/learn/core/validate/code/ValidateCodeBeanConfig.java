package com.security.learn.core.validate.code;

import com.security.learn.core.property.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;

/**
 * @author Harry Xu
 * @date 2019/11/28 17:36
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
    public ValidateCodeGenerator validateCodeGenerator(){
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        return codeGenerator;
    }
}


