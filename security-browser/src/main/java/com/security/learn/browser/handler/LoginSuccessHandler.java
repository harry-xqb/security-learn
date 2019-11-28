package com.security.learn.browser.handler;

import com.google.gson.Gson;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Harry Xu
 * @date 2019/11/28 13:56
 */
@Component
@Log
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public static String RETURN_TYPE = "html";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        if(StringUtils.equals("html", RETURN_TYPE)){
            super.setDefaultTargetUrl("/index");
            super.onAuthenticationSuccess(request, response, authentication);
        }
        log.info("登录成功");
        response.setContentType("application/json;charset=UTF-8");
        Map map = new HashMap();
        map.put("code", 0);
        map.put("msg", " success");
        map.put("data", authentication);
        response.getWriter().write(new Gson().toJson(map));
    }
}
