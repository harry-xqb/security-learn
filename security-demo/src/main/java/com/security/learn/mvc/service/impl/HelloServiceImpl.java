package com.security.learn.mvc.service.impl;

import com.security.learn.mvc.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * create by： harry
 * date:  2019/11/23 0023 下午 11:04
 **/
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void greeting(String name) {
        System.out.println("hello :" + name);
    }
}
