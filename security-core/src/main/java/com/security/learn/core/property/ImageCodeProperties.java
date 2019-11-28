package com.security.learn.core.property;

import lombok.Data;

/**
 * @author Harry Xu
 * @date 2019/11/28 16:41
 */
@Data
public class ImageCodeProperties {

    private int width = 67;

    private int height = 23;

    private int length = 4;

    private int expireIn = 60;

    private String url;
}
