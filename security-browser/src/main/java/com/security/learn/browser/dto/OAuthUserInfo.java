package com.security.learn.browser.dto;

import lombok.Data;

/**
 * create by： harry
 * date:  2019/12/1 0001 下午 3:39
 **/
@Data
public class OAuthUserInfo {

    private String providerId;

    private String providerUserId;

    private String username;

    private String avatar;
}
