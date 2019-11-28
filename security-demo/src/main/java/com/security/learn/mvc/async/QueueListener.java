package com.security.learn.mvc.async;

import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * create by： harry
 * date:  2019/11/24 0024 下午 5:13
 **/
@Log
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DeferredResultHolder deferredResultHolder;
    @Autowired
    private MockQueue mockQueue;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("队列监听器初始化成功");
        new Thread(() -> {
            while(true){
                if(StringUtils.isNotBlank(mockQueue.getCompletePlatedId())){
                    String orderId = mockQueue.getCompletePlatedId();
                    deferredResultHolder.getMap().get(orderId).setResult("Congratulations order success!");
                    log.info("队列监听器返回订单处理结果:" + orderId);
                    mockQueue.setCompletePlatedId(null);
                }else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
