package com.security.learn.mvc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.security.learn.mvc.dto.User;
import com.security.learn.mvc.exception.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * create by： harry
 * date:  2019/11/23 0023 下午 10:26
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/regist")
    public String regist(User user, HttpServletRequest request){
        // todo 注册用户逻辑
        String name = user.getName();
        providerSignInUtils.doPostSignUp(name,new ServletWebRequest(request));
        return "注册成功";
    }

    @GetMapping
    @JsonView(User.SimpleUserInfo.class)
    public List<User> list(){
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    @GetMapping("/{id}")
    @JsonView(User.DetailUserInfo.class)
    public User get(@PathVariable String id){
        System.out.println("获取用户信息:" + id);
        User user = new User();
        user.setId("123456");
        user.setName("harry");
        user.setPassword("123456");
        user.setBirthday(new Date(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()));
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user){
        System.out.println("创建用户:" + user);
        user.setId("123456");
        return user;
    }

    @PutMapping
    public User update(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            System.out.println("发生了错误:"  + errors);
            errors.getAllErrors().stream().forEach(error -> System.out.println(((FieldError)error).getField()));
        }
        System.out.println("更新用户:" + user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        System.out.println("删除用户:" + id);
    }

    @GetMapping("/login/{username}")
    public void login(@PathVariable String username){
        throw new UserException("用户登录异常", username);
    }

    @GetMapping("/server/error")
    public void error() throws Exception {
        throw new Exception("抛出了自定义的错误");
    }

    @GetMapping("/me")
    public Authentication getMe(Authentication authentication){
        return authentication;
    }
}
