package com.security.learn.core.validate.code;

import com.security.learn.core.property.SecurityProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Harry Xu
 * @date 2019/11/28 17:31
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(HttpServletRequest request) {
        //第一步 生成随机验证码 可以去网上搜
        int width = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getCode().getImage().getWidth());//宽和高
        int height = ServletRequestUtils.getIntParameter(request, "height", securityProperties.getCode().getImage().getHeight());
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman",Font.ITALIC,20));
        g.setColor(getRandColor(160,200));
        for(int i=0;i<155;i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl =random.nextInt(12);
            int yl =random.nextInt(12);
            g.drawLine(x, y, x+xl, y+yl);
        }

        String sRand = "";
        for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {//数字验证码长度
            String rand = String.valueOf(random.nextInt(10));
            sRand +=rand;
            g.setColor(new Color(20 + random.nextInt(110),
                    20 + random.nextInt(110),20 + random.nextInt(110)));
            g.drawString(rand, 13*i+6, 16);
        }
        g.dispose();
        return new ImageCode(image,sRand,securityProperties.getCode().getImage().getExpireIn());//有效期60秒
    }

    /**
     * 生成随机背景条纹
     */
    private Color getRandColor(int fc,int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }
}
