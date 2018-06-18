package com.xjy.security.Async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: XBlue
 * @Date: Create in 2018/6/1816:07
 * @Description: 这个只是单纯做消息队列
 * @Modified By:
 */
@Component
public class MockQueue {
    private static final Logger logger = LoggerFactory.getLogger(MockQueue.class);
    private String complaceOrder;
    public void setPlaceOrder(String placeOrder) {
//        这里之所以新起个线程，是因为消息队列不属于该系统的
        new Thread(()->{
            logger.info("接到下单请求"+placeOrder);
            try {
//                模拟消费
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.complaceOrder = placeOrder;
            logger.info("下单请求处理完成"+placeOrder);
        }).start();
    }
    public String getComplaceOrder() {
        return complaceOrder;
    }
    public void setComplaceOrder(String complaceOrder) {
        this.complaceOrder = complaceOrder;
    }

}
