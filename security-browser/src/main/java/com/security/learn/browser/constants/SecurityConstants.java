package com.security.learn.browser.constants;

/**
 * @author Harry Xu
 * @date 2019/11/29 9:32
 */
public class SecurityConstants {

    public static final String AUTHENTICATION_REQUIRE_PATH = "/authentication";

    public static final String LOGIN_REDIRECT_PATH = "/login";

    public static final String AUTHENTICATION_FORM_PATH = "/authentication/form";

    public static final String AUTHENTICATION_SMS_PATH = "/authentication/sms";

    public static final String CODE_IMAGE_PATH = "/code/*";

    public static final Integer REMEMBER_ME_VALIDATE_SECONDS = 20; // 一周内有效
}
