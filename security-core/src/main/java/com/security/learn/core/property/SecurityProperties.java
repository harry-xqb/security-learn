package com.security.learn.core.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Harry Xu
 * @date 2019/11/28 14:32
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
@ConfigurationProperties(prefix = "com.security.learn")
@Data
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();
}
