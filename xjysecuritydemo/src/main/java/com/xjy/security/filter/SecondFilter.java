package com.xjy.security.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1811:10
 * @Description:
 * @Modified By:
 */
@Slf4j
public class SecondFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("second in");
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("second out");
    }

    @Override
    public void destroy() {

    }
}
