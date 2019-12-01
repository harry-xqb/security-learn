package com.security.learn.core.property;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * create by： harry
 * date:  2019/12/1 0001 上午 1:37
 **/
@Data
public class GithubProperties extends SocialProperties {

    private String providerId = "github";
}
