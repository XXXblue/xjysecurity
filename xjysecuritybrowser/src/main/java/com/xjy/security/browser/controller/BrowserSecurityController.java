package com.xjy.security.browser.controller;

import com.xjy.security.browser.support.SimpleResponse;
import com.xjy.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/199:31
 * @Description: 这个类的作用是覆盖看看请求是html还是restful 如果是html返回登录页面，如果是restful返回json
 * @Modified By:
 */
@RestController
@Slf4j
public class BrowserSecurityController {
//    这里这个是springsecurity会把之前的请求存到session,这个sesson是有springsecurity管理，为什么要用这个东西
//    因为你没有认证，我给你重定向了，两次请求是不一样，你要拿到上一次请求的信息，这个时候就只能用requestCache去拿
//    任何时候都可以通过任意一个httpsessionRequestCache实例拿到你之前的请求
    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
/**
 *    上面这两个很难理解，说一个流程吧，如果你访问某个资源没有登录被重定向到登录也，这个时候的请求已经不是第一次的请求了，但是由于你使用了
 *    RedirectStrategy你的request会被缓存起来并且和这个重定向的心request产生关联。当你登录成功后，又一次发现权限不够，这个时候就要你重新登录
 *    （可能要你登录个vip账号之类的）,那我们怎么拿到第一访问的那个request呢，这个时候需要缓存中拿了，才能知道你第一次要访问的资源是什么。
 */

    @Autowired
    private SecurityProperties securityProperties;
    /**
     *  需要身份认证时跳转到这里
     * @param request
     * @return
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requestAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
       SavedRequest savedRequest = requestCache.getRequest(request,response);
       if(savedRequest != null){
           String target = savedRequest.getRedirectUrl();
           log.info(target);
           if(StringUtils.endsWithIgnoreCase(target,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
           }
       }
        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
    }
}
