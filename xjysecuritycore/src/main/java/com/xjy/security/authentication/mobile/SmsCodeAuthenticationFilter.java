package com.xjy.security.authentication.mobile;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.bouncycastle.cms.RecipientId.password;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/2019:58
 * @Description:
 * @Modified By:
 */
public class SmsCodeAuthenticationFilter  extends AbstractAuthenticationProcessingFilter{
    public static final String XJY_MOBILE_KEY = "mobile";
    private String mobileParameter = XJY_MOBILE_KEY;
    private boolean postOnly = true;
//手机认证的url在这里
    public SmsCodeAuthenticationFilter () {
        super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String mobile = this.obtainMobile(request);
            if (mobile == null) {
                mobile = "";
            }
            mobile = mobile.trim();
//            生成我们自己定义的token
            SmsCodeAuthenticationToken smsCodeAuthenticationToken = new SmsCodeAuthenticationToken(mobile);
            this.setDetails(request, smsCodeAuthenticationToken);
//            这一步相当于用token在调用manager
            return this.getAuthenticationManager().authenticate(smsCodeAuthenticationToken);
        }
    }

//    获取手机号的方法
    private String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }


    protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken smsCodeAuthenticationToken) {
        smsCodeAuthenticationToken.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "mobile parameter must not be empty or null");
        this.mobileParameter = mobileParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMobileParameter() {
        return this.mobileParameter;
    }

}
