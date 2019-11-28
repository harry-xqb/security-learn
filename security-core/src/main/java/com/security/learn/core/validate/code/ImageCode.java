package com.security.learn.core.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author Harry Xu
 * @date 2019/11/28 15:40
 */
@Data
public class ImageCode {

    private BufferedImage bufferedImage;

    private String code;

    private LocalDateTime expireTime;

    public ImageCode(BufferedImage bufferedImage, String code, int expireIn) {
        this.bufferedImage = bufferedImage;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
