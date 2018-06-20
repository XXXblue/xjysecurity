package com.xjy.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/199:50
 * @Description: 暴露配置给demo服务层配置用
 * @Modified By:
 */
@ConfigurationProperties(prefix = "xjy.security")
public class SecurityProperties {
   private BrowserProperties browser = new BrowserProperties();
//    这些名字很重要，配置就是通过这些名字进行配置的
   private ValidateCodeProperties code = new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
