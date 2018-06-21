package com.xjy.security.validate.code;
import com.xjy.security.properties.SecurityProperties;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @author zhailiang
 *  短信验证码的具体实现类
 */
public class SmsCodeGenerator implements ValidateCodeGenerator{

    private SecurityProperties securityProperties;

    public ValidateCode generator(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
