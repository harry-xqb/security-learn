package com.security.learn.core.property;

import lombok.Data;

/**
 * @author Harry Xu
 * @date 2019/11/28 16:44
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private GeneralValidateCodeProperties sms = new GeneralValidateCodeProperties();

    private ALiYunProperties aliyun = new ALiYunProperties();
}
