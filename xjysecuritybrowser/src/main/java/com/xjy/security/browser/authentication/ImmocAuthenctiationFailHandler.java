package com.xjy.security.browser.authentication;

import com.alibaba.fastjson.JSON;
import com.xjy.security.properties.LoginType;
import com.xjy.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1915:06
 * @Description:    自定义登录错误后的处理
 * @Modified By:
 */
@Component("immocAuthenctiationFailHandler")
@Slf4j
public class ImmocAuthenctiationFailHandler extends SimpleUrlAuthenticationFailureHandler{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            log.info("登录失败");
//        可以把这两句的作用理解为@ResponseBody
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(e));
        }else{
            super.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
        }
}
}
