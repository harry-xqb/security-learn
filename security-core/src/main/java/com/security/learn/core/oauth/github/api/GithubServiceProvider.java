package com.security.learn.core.oauth.github.api;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * create by： harry
 * date:  2019/12/1 0001 上午 1:02
 **/
public class GithubServiceProvider extends AbstractOAuth2ServiceProvider<GithubService> {

    public static final String GITHUB_GET_AUTHORIZE_URL = "https://github.com/login/oauth/authorize";

    public static final String GITHUB_GET_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";


    public GithubServiceProvider(String appId, String appSecret) {
        super(new OAuth2Template(appId, appSecret, GITHUB_GET_AUTHORIZE_URL, GITHUB_GET_ACCESS_TOKEN_URL));
    }

    @Override
    public GithubService getApi(String accessToken) {
        return new GithubServiceImpl(accessToken);
    }
}
