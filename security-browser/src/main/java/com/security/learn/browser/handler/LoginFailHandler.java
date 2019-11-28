package com.security.learn.browser.handler;

import com.google.gson.Gson;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Harry Xu
 * @date 2019/11/28 14:03
 */
@Component
@Log
public class LoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.info("登录失败");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
        Map map = new HashMap();
        map.put("code", 500);
        map.put("msg", " login fail");
        map.put("data", exception);
        response.getWriter().write(new Gson().toJson(map));
    }
}
