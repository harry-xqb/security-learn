package com.security.learn.core.authentication;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author Harry Xu
 * @date 2019/11/29 16:09
 */
@Data
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
         SmsAuthenticationToken smsAuthenticationToken = (SmsAuthenticationToken)authentication;
         String phone = (String) smsAuthenticationToken.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(phone);
        // 校权成功后 新建一个Token
        SmsAuthenticationToken authenticationTokenResult = new SmsAuthenticationToken(userDetails, userDetails.getAuthorities());
        authenticationTokenResult.setDetails(smsAuthenticationToken.getDetails());
        return authenticationTokenResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
