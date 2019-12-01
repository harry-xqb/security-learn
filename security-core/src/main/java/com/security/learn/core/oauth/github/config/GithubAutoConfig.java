package com.security.learn.core.oauth.github.config;

import com.security.learn.core.oauth.github.connect.GithubConnectionFactory;
import com.security.learn.core.oauth.view.OAuthConnectView;
import com.security.learn.core.property.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * create by： harry
 * date:  2019/12/1 0001 上午 1:42
 **/
@Configuration
@ConditionalOnProperty(prefix = "com.security.learn.oauth.github", name = "appId")
public class GithubAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new GithubConnectionFactory(securityProperties.getOauth().getGithub().getProviderId(),
                securityProperties.getOauth().getGithub().getAppId(),
                securityProperties.getOauth().getGithub().getAppSecret());
    }

    @Bean({"connect/githubConnected", "connect/githubConnect"})
    @ConditionalOnMissingBean(name = "githubConnectedView")
    public View githubView(){
        return new OAuthConnectView();
    }
}
