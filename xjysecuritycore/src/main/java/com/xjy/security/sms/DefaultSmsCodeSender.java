package com.xjy.security.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/2017:24
 * @Description:
 * @Modified By:
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        log.info("向手机"+mobile+"发送短信验证码"+code);
    }
}
