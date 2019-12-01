package com.security.learn.browser.config;

import com.security.learn.browser.constants.SecurityConstants;
import com.security.learn.browser.session.SecuritySessionStrategy;
import com.security.learn.core.authentication.SmsCodeAuthenticationSecurityConfig;
import com.security.learn.core.property.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.sql.DataSource;

/**
 * @author Harry Xu
 * @date 2019/11/28 13:08
 */
@Configuration
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private OncePerRequestFilter imageCodeFilter;
    @Autowired
    private OncePerRequestFilter smsCodeFilter;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
    @Autowired
    private SpringSocialConfigurer securityOAuthConfig;
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        SecurityConstants.AUTHENTICATION_REQUIRE_PATH,
                        SecurityConstants.LOGIN_PATH,
                        SecurityConstants.CODE_IMAGE_PATH,
                        SecurityConstants.AUTHENTICATION_SMS_PATH,
                        "/user/regist",
                        "/oauth/user",
                        "/session/invalid",
                        securityProperties.getBrowser().getSignUpUrl()
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(SecurityConstants.AUTHENTICATION_REQUIRE_PATH)
                .loginProcessingUrl(SecurityConstants.AUTHENTICATION_FORM_PATH)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .sessionManagement()
                .invalidSessionUrl("/session/invalid")
                .maximumSessions(1)
                .expiredSessionStrategy(new SecuritySessionStrategy())
                .and()
                .and()
                .logout()
                .logoutUrl("/signOut")
                .logoutSuccessUrl("/user/regist")
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(SecurityConstants.REMEMBER_ME_VALIDATE_SECONDS)
                .userDetailsService(userDetailsService)
                .and()
                .addFilterBefore(imageCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
        http.apply(smsCodeAuthenticationSecurityConfig);
        http.apply(securityOAuthConfig);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}