package com.security.learn.browser.controller;

import com.security.learn.browser.dto.User;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Harry Xu
 * @date 2019/11/28 16:59
 */
@RestController
@RequestMapping("/user")
@Log
public class UserController {


    @PostMapping
    public User create(){
        User user = new User("111", "harry", "123", LocalDateTime.now());
        return user;
    }

    @GetMapping("{id}")
    public User get(@PathVariable String id){
        log.info("id:" + id);
        return new User();
    }
}
