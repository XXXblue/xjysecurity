package com.xjy.security.config;

import com.google.common.collect.Lists;
import com.xjy.security.filter.SecondFilter;
import com.xjy.security.filter.TimeFilter;
import com.xjy.security.intceptor.SecondInterceptor;
import com.xjy.security.intceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1811:03
 * @Description: servlet的filter配置
 * @Modified By:
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Autowired
    private TimeInterceptor timeInterceptor;
    @Autowired
    private SecondInterceptor secondInterceptor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterceptor);
//        registry.addInterceptor(secondInterceptor);
//    }
    //    @Bean
//    public FilterRegistrationBean secondFilter(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
////      那个拦截器生效
//        SecondFilter secondFilter = new SecondFilter();
//        registrationBean.setFilter(secondFilter);
//        List<String> urls = new ArrayList<>();
//        urls.add("/*");
//        registrationBean.setUrlPatterns(urls);
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean timerFilter(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
////      那个拦截器生效
//        TimeFilter timeFilter = new TimeFilter();
//        registrationBean.setFilter(timeFilter);
////      拦截那些url
//        List<String> urls = new ArrayList<>();
//        urls.add("/*");
//        registrationBean.setUrlPatterns(urls);
//        return registrationBean;
//    }

}
