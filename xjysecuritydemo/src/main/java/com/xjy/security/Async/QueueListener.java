package com.xjy.security.Async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1816:10
 * @Description: 这个模拟消息队列和第二个应用
 * @Modified By:
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);

    @Autowired
    private MockQueue moQueue;
    @Autowired
    private DeferredResultHalder deferredResultHalder;
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //无限循环
        new Thread(()->{
            while(true) {
                if(moQueue.getComplaceOrder()!=null&&!moQueue.getComplaceOrder().equals("")) {
                    String orderNumber=moQueue.getComplaceOrder();
                    logger.info("返回订单处理结果"+orderNumber);
                    //什么时候设置了值，什么时候就会真正返回
                    deferredResultHalder.getMap().get(orderNumber).setResult("place order success");
                    moQueue.setComplaceOrder(null);
                }else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
