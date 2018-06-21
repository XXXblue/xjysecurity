package com.xjy.security.controller;

import com.xjy.security.properties.SecurityProperties;
import com.xjy.security.sms.SmsCodeSender;
import com.xjy.security.validate.code.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1917:08
 * @Description:
 * @Modified By:
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    public static final String SMS_KEY="SESSION_KEY_SMS_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//图片验证码生成类
    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;
//短信验证码生成类
    @Autowired
    private ValidateCodeGenerator smsValidateCodeGenerator;
//短信验证码发送处理类
    @Autowired
    private SmsCodeSender smsCodeSender;
//    发送图形验证码接口
    @GetMapping("/code/image")
    public void createCode(HttpServletResponse response, HttpServletRequest request) throws IOException {
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generator(new ServletWebRequest(request));
//        存session，这里存spring管理的session,切记一点就是你用spring管理的session就必须用spring封装的requset去获取
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }

//   发送短信验证码接口
    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletResponse response, HttpServletRequest request) throws ServletRequestBindingException {
        //生成短信验证码存到session中
        ValidateCode smsCode = smsValidateCodeGenerator.generator(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request),SMS_KEY,smsCode );
        //拿到你的手机号和放在session中的短信验证码，通过smsCoder发送给你的手机
        //在这里补充你具体通过短信服务商发送短信验证的逻辑,默认只是打印，没有实现具体逻辑，给你去覆盖。
        String mobile = ServletRequestUtils.getRequiredStringParameter(request,"mobile");
        smsCodeSender.send(mobile,smsCode.getCode());
    }

}
