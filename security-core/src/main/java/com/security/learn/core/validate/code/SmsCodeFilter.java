package com.security.learn.core.validate.code;

import com.security.learn.core.property.SecurityProperties;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Harry Xu
 * @date 2019/11/29 16:20
 */
@Log
@Component
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private SecurityProperties securityProperties;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    private Set<String> urls = new HashSet<>();
    @Override
    public void afterPropertiesSet() throws ServletException {
        String[] configUrls = StringUtils.splitByWholeSeparator(securityProperties.getCode().getSms().getUrl(), ",");
        if(configUrls != null){
            for(String url: configUrls){
                urls.add(url);
            }
        }
        urls.add("/authentication/sms");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean flag = false;
        for(String url: urls){
            if(antPathMatcher.match(url, request.getRequestURI())){
                flag = true;
                break;
            }
        }
        if(flag){
            try{
                validate(request);
            }catch (ValidateCodeException e){
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) throws ValidateCodeException {
        String codeInRequest = request.getParameter("smsCode");
        if(!StringUtils.isNotBlank(codeInRequest)){
            throw new ValidateCodeException("验证码不能为空");
        }
        ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(new ServletWebRequest(request), ValidateCodeController.SESSION_SMS_KEY);
        if(codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }
        if(codeInSession.isExpired()){
            throw new ValidateCodeException("验证码已过期");
        }
        if(!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)){
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(new ServletWebRequest(request), ValidateCodeController.SESSION_SMS_KEY);
    }
}
