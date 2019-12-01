package com.security.learn.core.oauth;

import lombok.extern.java.Log;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * create by： harry
 * date:  2019/12/1 0001 下午 2:48
 **/
@Log
public class SecurityOAuthConfig extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public SecurityOAuthConfig(String filterProcessesUrl){
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        log.info("过滤器配置正在生效》。。。。。。。。。。");
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter) super.postProcess(object);
        socialAuthenticationFilter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) socialAuthenticationFilter;
    }
}
