package com.xjy.security.properties;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/199:51
 * @Description:  暴露出去的具体配置项，这个配置项主要是登录页面的选择以及登录成功失败后的放回方式是json还redirest，在successhandler和failhander里面调用
 * @Modified By:
 */
public class BrowserProperties {

    private String  loginPage = "/login.html";

    private LoginType loginType = LoginType.JSON;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
