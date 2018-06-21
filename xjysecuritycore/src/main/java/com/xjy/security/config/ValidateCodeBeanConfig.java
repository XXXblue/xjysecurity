package com.xjy.security.config;

import com.xjy.security.properties.SecurityProperties;
import com.xjy.security.sms.DefaultSmsCodeSender;
import com.xjy.security.sms.SmsCodeSender;
import com.xjy.security.validate.code.ImageCodeGenerator;
import com.xjy.security.validate.code.SmsCodeGenerator;
import com.xjy.security.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/2012:12
 * @Description: 用这种方式配置的bean主要是为了后期覆盖重构逻辑
 * @Modified By:
 */
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;
//这种bean的返回类型名字的小写开头就是你bean名字，所以要注意
//    图片验证码生成默认类
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeGenerator")
//    暴露接口，返回具体实现类
    public ValidateCodeGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }
//      短信验证码生成默认类
    @Bean
    @ConditionalOnMissingBean(name = "smsValidateCodeGenerator")
//    暴露接口，返回具体实现类
    public ValidateCodeGenerator smsValidateCodeGenerator(){
        SmsCodeGenerator smsCodeGenerator = new SmsCodeGenerator();
        smsCodeGenerator.setSecurityProperties(securityProperties);
        return smsCodeGenerator;
    }
//      短信验证码发送默认类
    @Bean
    @ConditionalOnMissingBean(name = "smsCodeSender")
//    暴露接口，返回具体实现类
    public SmsCodeSender smsCodeSender(){
        SmsCodeSender smsCodeSender =new DefaultSmsCodeSender();
        return smsCodeSender;
    }
}
