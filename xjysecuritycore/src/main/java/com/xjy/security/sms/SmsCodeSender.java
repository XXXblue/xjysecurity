package com.xjy.security.sms;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/2017:21
 * @Description:  和图片一样，图片是展示到前台给客户看，短信验证码是发送短信给用户看，我们抽象出一个发送的接口，默认一个发送类，用户可用默认类也可扩展
 * @Modified By:
 */
public interface SmsCodeSender {
    void send(String mobile,String code);
}
