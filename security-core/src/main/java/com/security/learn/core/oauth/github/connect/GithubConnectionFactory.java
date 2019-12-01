package com.security.learn.core.oauth.github.connect;

import com.security.learn.core.oauth.github.api.GithubService;
import com.security.learn.core.oauth.github.api.GithubServiceProvider;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

/**
 * create by： harry
 * date:  2019/12/1 0001 上午 1:17
 **/
public class GithubConnectionFactory extends OAuth2ConnectionFactory<GithubService> {


    public GithubConnectionFactory(String providerId, String addId, String appSecret) {
        super(providerId, new GithubServiceProvider(addId, appSecret), new GithubAdapter());
    }
}
