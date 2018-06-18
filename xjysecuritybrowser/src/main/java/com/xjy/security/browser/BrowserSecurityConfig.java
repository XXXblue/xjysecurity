package com.xjy.security.browser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1817:37
 * @Description:
 * @Modified By:
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
       http.formLogin()
               .and()
               .authorizeRequests()
//               对任何请求
               .anyRequest()
//               都需要身份认证
               .authenticated();
    }
}
