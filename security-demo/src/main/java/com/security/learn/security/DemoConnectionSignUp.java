package com.security.learn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * create by： harry
 * date:  2019/12/1 0001 下午 4:10
 **/
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        String displayName = connection.getDisplayName();
        return displayName;
    }
}
