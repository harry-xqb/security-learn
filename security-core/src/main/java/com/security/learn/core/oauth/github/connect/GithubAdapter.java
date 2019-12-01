package com.security.learn.core.oauth.github.connect;

import com.security.learn.core.oauth.github.api.GithubInfo;
import com.security.learn.core.oauth.github.api.GithubService;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * create by： harry
 * date:  2019/12/1 0001 上午 1:13
 **/
public class GithubAdapter implements ApiAdapter<GithubService> {
    @Override
    public boolean test(GithubService api) {
        return true;
    }

    @Override
    public void setConnectionValues(GithubService api, ConnectionValues values) {
        GithubInfo githubInfo = api.getGithubInfo();
        values.setDisplayName(githubInfo.getName());
        values.setImageUrl(githubInfo.getAvatar_url());
        values.setProviderUserId(githubInfo.getLogin());
        values.setProfileUrl(githubInfo.getHtml_url());
    }

    @Override
    public UserProfile fetchUserProfile(GithubService api) {
        return null;
    }

    @Override
    public void updateStatus(GithubService api, String message) {
        // TODO do nothing
    }
}
