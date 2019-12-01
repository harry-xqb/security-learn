package com.security.learn.core.property;

import lombok.Data;

/**
 * @author Harry Xu
 * @date 2019/11/28 14:32
 */
@Data
public class BrowserProperties {

    private String loginPage = "login.html";

    private String signUpUrl = "/signUp.html";
}
