package com.security.learn.core.property;

import lombok.Data;

/**
 * @author Harry Xu
 * @date 2019/11/29 10:58
 */
@Data
public class GeneralValidateCodeProperties {

    private int length = 6;

    private int expireIn = 60;

    private String url;
}
