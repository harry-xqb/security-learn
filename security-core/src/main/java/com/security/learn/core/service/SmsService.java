package com.security.learn.core.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

/**
 * @author Harry Xu
 * @date 2019/11/29 14:29
 */
public interface SmsService {

    SendSmsResponse sendSms(String phone, String code) throws ClientException;
}
