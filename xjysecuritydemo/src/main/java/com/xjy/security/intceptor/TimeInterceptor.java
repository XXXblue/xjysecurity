package com.xjy.security.intceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1811:29
 * @Description:
 * @Modified By:
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {
    private static final String START_TIME = "start_time";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("preHandler");
        HandlerMethod handlerMethod = (HandlerMethod) o;
        log.info("=======controller======={}",handlerMethod.getBean().getClass().getName());
        log.info("======method========{}",handlerMethod.getMethod().getName());
        httpServletRequest.setAttribute(START_TIME,System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandler");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletionHandler");
        log.info("========duiring time============{}",System.currentTimeMillis()-(Long)(httpServletRequest.getAttribute(START_TIME)));
    }
}
