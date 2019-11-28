package com.security.learn.mvc.validator.handle;

import com.security.learn.mvc.service.HelloService;
import com.security.learn.mvc.validator.anotation.NotExist;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * create by： harry
 * date:  2019/11/23 0023 下午 10:58
 **/
public class NotExistHandle implements ConstraintValidator<NotExist, Object> {

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(NotExist constraintAnnotation) {
        System.out.println("注解被初始化......");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println("value:" + value);
        helloService.greeting((String) value);
        return false;
    }
}
