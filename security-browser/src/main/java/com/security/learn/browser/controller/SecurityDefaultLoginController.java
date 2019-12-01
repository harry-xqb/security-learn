package com.security.learn.browser.controller;

import com.security.learn.browser.constants.SecurityConstants;
import com.security.learn.browser.dto.OAuthUserInfo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Harry Xu
 * @date 2019/11/28 13:34
 */
/**
 * 响应状态码 UNAUTHORIZED(401, "Unauthorized")
 */
@Controller
@Log
public class SecurityDefaultLoginController {
    /**
     * 重定向 策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 把当前的请求缓存到 session 里去
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 当需要身份认证时 跳转到这里
     */
    @RequestMapping("/authentication")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Map requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //拿到请求对象
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null){
            //获取 跳转url
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("请求跳转url:" + targetUrl);
            //判断 targetUrl 是不是 .html　结尾, 如果是：跳转到登录页(返回view)
            if (StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response, SecurityConstants.LOGIN_PATH);
            }
        }
        //如果不是，返回一个json 字符串
        Map map = new HashMap();
        map.put("code", "401");
        map.put("msg", "未认证");
        return map;
    }

    @GetMapping("/oauth/user")
    @ResponseBody
    public OAuthUserInfo getOAuthUserInfo(HttpServletRequest request){
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        OAuthUserInfo oAuthUserInfo = new OAuthUserInfo();
        oAuthUserInfo.setUsername(connection.getDisplayName());
        oAuthUserInfo.setProviderId(connection.getKey().getProviderId());
        oAuthUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
        oAuthUserInfo.setAvatar(connection.getImageUrl());
        return oAuthUserInfo;
    }

    @GetMapping("/session/invalid")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public Map sessionInvalid(){
        Map map = new HashMap();
        map.put("msg", "session 已失效");
        return map;
    }
}
