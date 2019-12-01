package com.security.learn.core.oauth;

import com.security.learn.core.oauth.jdbc.SecurityJdbcUserConnectionRepository;
import com.security.learn.core.property.SecurityProperties;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * create by： harry
 * date:  2019/12/1 0001 上午 1:24
 **/
@Configuration
@EnableSocial
@Log
public class OAuthConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired(required =  false)
    private ConnectionSignUp connectionSignUp;


    @Override
    @Bean
    @Primary
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        SecurityJdbcUserConnectionRepository repository = new SecurityJdbcUserConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        repository.setTablePrefix("security_");
        if(connectionSignUp != null){
            log.info("无需注册，第三方直接登录..........");
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    @Bean
    public SpringSocialConfigurer springSocialConfigurer(){
        SecurityOAuthConfig securityOAuthConfig = new SecurityOAuthConfig(securityProperties.getOauth().getFilterProcessesUrl());
        securityOAuthConfig.signupUrl(securityProperties.getBrowser().getSignUpUrl());
        return securityOAuthConfig;
    }

    @Bean
    public  ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }
}
