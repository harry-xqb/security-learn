package com.security.learn.core.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Harry Xu
 * @date 2019/11/28 15:40
 */
@Data
public class ImageCode extends ValidateCode implements Serializable {

    private BufferedImage bufferedImage;

    public ImageCode(BufferedImage bufferedImage, String code, int expireIn){
        super(code, expireIn);
        this.bufferedImage = bufferedImage;
    }


}
