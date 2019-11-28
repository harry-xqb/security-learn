package com.security.learn.mvc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Date;

/**
 * create by： harry
 * date:  2019/11/24 0024 下午 3:48
 **/
/*@Component
@Aspect*/
public class TimeAspect {

   // @Around("execution (* com.harry.sercurity.controller.UserController.*(..))")
    public Object handlerUserControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start----");
        Long start = new Date().getTime();
        Object[] args = pjp.getArgs();
        for(Object arg: args){
            System.out.println("arg:" + arg);
        }
        Object proceed = pjp.proceed();
        System.out.println("time aspect end----耗时:" + (new Date().getTime() - start));
        return null;
    }
}
