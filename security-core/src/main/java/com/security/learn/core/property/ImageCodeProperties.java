package com.security.learn.core.property;

import lombok.Data;

/**
 * @author Harry Xu
 * @date 2019/11/28 16:41
 */
@Data
public class ImageCodeProperties extends GeneralValidateCodeProperties{

    private int width = 67;

    private int height = 23;

    public ImageCodeProperties(){
        setLength(4);
    }
}
