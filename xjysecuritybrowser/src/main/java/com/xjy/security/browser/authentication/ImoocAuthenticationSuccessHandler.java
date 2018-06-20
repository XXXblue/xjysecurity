package com.xjy.security.browser.authentication;

import com.alibaba.fastjson.JSON;
import com.xjy.security.properties.LoginType;
import com.xjy.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1910:38
 * @Description:  自定义登录成功后的处理
 * @Modified By:
 */
@Component("imoocAuthenticationSuccessHandler")
@Slf4j
public class ImoocAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        log.info("登录成功");
//        如果是json
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            //        可以把这两句的作用理解为@ResponseBody
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(authentication));
        }else{
//            如果不是调用父类的方法正常跳转
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }
    }
}
