package com.xjy.security.Async;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1816:09
 * @Description: 这玩意充当开启新线程
 * @Modified By:
 */
@Component
public class DeferredResultHalder {
//键===订单号  值===返回结果
    private Map<String, DeferredResult<String>> map=new HashMap<String,DeferredResult<String>>();

    public Map<String, DeferredResult<String>> getMap() {
        return map;
    }

    public void setMap(Map<String, DeferredResult<String>> map) {
        this.map = map;
    }
}