package com.xjy.security.authentication.mobile;

import com.xjy.security.controller.ValidateCodeController;
import com.xjy.security.exception.ValidateCodeException;
import com.xjy.security.properties.SecurityProperties;
import com.xjy.security.validate.code.ValidateCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1919:06
 * @Description: 图形验证码校验过滤器，以后记住了，凡事校验都是在过滤器中进行的,你会发现其实filter需要用到的配置和handler都是set进来的不是注入的。
 * @Modified By:
 */
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean{

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

//    这个是用来解决/user/*这种拦截的
    private AntPathMatcher pathMatcher = new AntPathMatcher();

//    实现InitializationBean才能让该bean在实例化前，把一些配置初始化好，这个方法要手动调用
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        if(!StringUtils.isBlank(securityProperties.getCode().getSms().getUrl())){
            String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getSms().getUrl(),",");
            for(String url:configUrls){
                urls.add(url);
            }
        }
        urls.add("/authentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        拿出所有的配置请求
        boolean action = false;
        for(String url:urls){
            if(pathMatcher.match(url,httpServletRequest.getRequestURI())){
                action = true;
            }
        }
//        request的请求url和配置请求如果存在匹配
        if(action){
            try{
                validate(new ServletWebRequest(httpServletRequest));
            }catch(ValidateCodeException e){
//                失败了走哪里，这里指定走自定义的失败处理器
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
//                这个return很关键，就是不让代码往下走，不然会发生多次response.getwriter的错误
                return;
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        //session中的code值
        ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(request, ValidateCodeController.SMS_KEY);
        //表单传上来的code值
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"smsCode");
        if(StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("手机验证码的值不能为空");
        }
        if(codeInSession == null){
            throw new ValidateCodeException("手机验证码不存在");
        }
        if(codeInSession.isExpried()){
            sessionStrategy.removeAttribute(request,ValidateCodeController.SMS_KEY);
            throw new ValidateCodeException("手机验证码已过期");
        }
        if(!StringUtils.equals(codeInRequest,codeInSession.getCode())){
            throw new ValidateCodeException("手机验证码不匹配");
        }
        sessionStrategy.removeAttribute(request,ValidateCodeController.SMS_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
