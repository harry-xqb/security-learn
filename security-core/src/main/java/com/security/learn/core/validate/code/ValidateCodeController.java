package com.security.learn.core.validate.code;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.learn.core.property.SecurityProperties;
import com.security.learn.core.service.SmsService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Harry Xu
 * @date 2019/11/28 15:53
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    public static final String SESSION_SMS_KEY = "SESSION_KEY_SYS_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsService smsService;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(request);
        ValidateCode code = new ValidateCode(imageCode.getCode(), imageCode.getExpireTime());
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, code);
        ImageIO.write(imageCode.getBufferedImage(), "JPEG", response.getOutputStream());
    }

    @GetMapping("/code/sms")
    public Map<String, Object> createSmsCode(@RequestParam String phone, HttpServletRequest request){
        ValidateCode smsCode = smsCodeGenerator.generate(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_SMS_KEY, smsCode);
        Map map = new HashMap();
        map.put("code", "0");
        map.put("msg", "短信发送成功");
       /* try{
            SendSmsResponse sendSmsResponse = smsService.sendSms(phone, smsCode.getCode());
            if(StringUtils.equalsIgnoreCase(sendSmsResponse.getCode(), "ok")){
                map.put("code", "0");
                map.put("msg", "短信发送成功");
                return map;
            }else{
                map.put("code", "500");
                map.put("msg", "短信发送失败");
                map.put("data", sendSmsResponse.getMessage());
                return map;
            }
        }catch (ClientException e){
            e.printStackTrace();
            map.put("code", "500");
            map.put("msg", "短信发送失败");
            return map;
        }*/
       return map;
    }

}
