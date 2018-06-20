package com.xjy.security.browser;

import com.xjy.security.browser.authentication.ImmocAuthenctiationFailHandler;
import com.xjy.security.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.xjy.security.filter.ValidateCodeFilter;
import com.xjy.security.properties.SecurityProperties;
import com.xjy.security.validate.code.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1817:37
 * @Description:  配置拦截规则，使用表单验证或者弹出层验证,这一块相当于配置文件
 * @Modified By:
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ImoocAuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
    @Autowired
    private ImmocAuthenctiationFailHandler immocAuthenctiationFailHandler;
//    配置srpingsecurity加密解密的类
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        验证码的校验
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//        设置失败处理
           validateCodeFilter.setAuthenticationFailureHandler(immocAuthenctiationFailHandler);
//          配置过滤器配置项
            validateCodeFilter.setSecurityProperties(securityProperties);
//            初始化过滤器的配置
            validateCodeFilter.afterPropertiesSet();
//        http.httpBasic()
            http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
               .formLogin()
//               跳转到自定义登录页
               .loginPage("/authentication/require")
//               提交表单的url
               .loginProcessingUrl("/authentication/form")
               //配置自定义登录成功后的处理
               .successHandler(imoocAuthenticationSuccessHandler)
               .failureHandler(immocAuthenctiationFailHandler)
               .and()
               .authorizeRequests()
//               放行公页
               .antMatchers("/authentication/require",
                       securityProperties.getBrowser().getLoginPage()
                        ,"/code/image"
                        ,"/login.html").permitAll()
//               对任何请求
               .anyRequest()
//               都需要身份认证
               .authenticated()
                .and()
                .csrf().disable();
    }
}
