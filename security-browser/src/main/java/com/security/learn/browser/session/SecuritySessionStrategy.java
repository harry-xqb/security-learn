package com.security.learn.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.RequestAttributes;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * create by： harry
 * date:  2019/12/1 0001 下午 8:14
 **/
public class SecuritySessionStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        event.getResponse().setContentType("text/html;charset=UTF-8;");

        event.getResponse().getWriter().write("<h3>并发登录</h3>");
    }
}
