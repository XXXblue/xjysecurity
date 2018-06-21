package com.xjy.security.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/219:26
 * @Description:
 * @Modified By:
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;
//    校验的逻辑,很简单就是通过userDetailService拿到用户信息填充到token中，并且将token的状态改为已认证状态
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if(user == null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }
//        封装detailService从数据库查询出来的信息到token中，把token的未认证状态改为已认证状态
        SmsCodeAuthenticationToken smsCodeAuthenticationTokenResult = new SmsCodeAuthenticationToken(user,user.getAuthorities());
//        设置请求信息
        smsCodeAuthenticationTokenResult.setDetails(authenticationToken.getDetails());
        return smsCodeAuthenticationTokenResult;
    }
//支持哪种token校验,这里我们校验传进来的是不是sms自定义的token，是返回true，不是返回false
    @Override
    public boolean supports(Class<?> aClass) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
