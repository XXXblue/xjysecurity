package com.xjy.security.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1810:44
 * @Description: 使用filter是计算请求时间的,但是这个是servlet的内置的，拿不到controller等这些方法信息
 * @Modified By:
 */
@Slf4j
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("timeFilter---------init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("time filter start");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("request time {}",(System.currentTimeMillis()-start));
        log.info("time filter end");
    }

    @Override
    public void destroy() {
        log.info("timeFilter---------destory");
    }
}
