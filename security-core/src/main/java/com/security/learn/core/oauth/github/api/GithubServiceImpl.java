package com.security.learn.core.oauth.github.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

/**
 * create by： harry
 * date:  2019/12/1 0001 上午 12:36
 **/
@Log
public class GithubServiceImpl extends AbstractOAuth2ApiBinding implements GithubService {

    public static final String GITHUB_GET_USER_INFO_URL = "https://api.github.com/user";

    public GithubServiceImpl(String accessToken){
        super(accessToken);
    }

    @Override
    public GithubInfo getGithubInfo() {
        String result = getRestTemplate().getForObject(GITHUB_GET_USER_INFO_URL, String.class);
        log.info("result:" + result);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            GithubInfo githubInfo = objectMapper.readValue(result, GithubInfo.class);
            return githubInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}
