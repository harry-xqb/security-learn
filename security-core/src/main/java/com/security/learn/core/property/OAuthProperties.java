package com.security.learn.core.property;

import lombok.Data;

/**
 * create by： harry
 * date:  2019/12/1 0001 上午 1:39
 **/
@Data
public class OAuthProperties {

    private GithubProperties github = new GithubProperties();

    private String filterProcessesUrl = "/auth";
}
