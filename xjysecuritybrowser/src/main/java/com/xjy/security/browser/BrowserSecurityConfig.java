package com.xjy.security.browser;

import com.alibaba.druid.pool.DruidDataSource;
import com.xjy.security.authentication.mobile.SmsCodeAuthenticationFilter;
import com.xjy.security.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.xjy.security.authentication.mobile.SmsCodeFilter;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1817:37
 * @Description: 配置拦截规则，使用表单验证或者弹出层验证,这一块相当于配置文件
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
    @Autowired
    private DruidDataSource druidDataSource;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    //    配置记住我的功能,通过看流程记住我的功能是需要数据库的，所以在这里要使用数据源，这里我试着druid数据源
    //具体实现步骤，第一步要有数据源，第二部要有userdetailservice,第三步配置这个，第四步在下面configure方法中配置记住我的配置
    //    配置非常简单
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(druidDataSource);
//        检查是否有数据库，没有就创建，这个要注意，这个只是为你省去你建表的麻烦，如果使用了这个，在此启动要注释掉它
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    //    配置srpingsecurity加密解密的类
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        验证码的校验
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
//        设置失败处理
        validateCodeFilter.setAuthenticationFailureHandler(immocAuthenctiationFailHandler);
//          配置验证码过滤器配置项
        validateCodeFilter.setSecurityProperties(securityProperties);
//            初始化验证码过滤器的配置
        validateCodeFilter.afterPropertiesSet();


        //手机验证码的校验
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
//        设置失败处理
        smsCodeFilter.setAuthenticationFailureHandler(immocAuthenctiationFailHandler);
//          配置验证码过滤器配置项
        smsCodeFilter.setSecurityProperties(securityProperties);
//            初始化验证码过滤器的配置
        smsCodeFilter.afterPropertiesSet();


//        http.httpBasic()
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
               . addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
//               跳转到自定义登录页
                .loginPage("/authentication/require")
//               提交表单的url
                .loginProcessingUrl("/authentication/form")
                //配置自定义登录成功后的处理
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(immocAuthenctiationFailHandler)
                .and()
//                rememberme配置start
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
//              记住我配置成功后用userDetailService去登录
                .userDetailsService(userDetailsService)
//                rememberme配置end
                .and()
                .authorizeRequests()
//               放行公页
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage()
                        , "/code/*"
                        , "/login.html"
                        ).permitAll()
//               对任何请求
                .anyRequest()
//               都需要身份认证
                .authenticated()
                .and()
                .csrf().disable()
//                添加手机验证的配置
                .apply(smsCodeAuthenticationSecurityConfig);
    }
}
