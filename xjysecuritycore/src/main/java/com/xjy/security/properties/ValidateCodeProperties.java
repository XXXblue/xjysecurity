package com.xjy.security.properties;


/**
 * @Author: XBlue
 * @Date: Create in 2018/6/2010:50
 * @Description:  图片验证码配置不会直接暴露给securityProperties，而是给这个，主要是后面还要有短信验证码，它们都属于校验，用一个validatecodeproperties给管理其起来
 * @Modified By:
 */
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
