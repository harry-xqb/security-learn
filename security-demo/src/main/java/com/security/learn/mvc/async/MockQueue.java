package com.security.learn.mvc.async;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

/**
 * create by： harry
 * date:  2019/11/24 0024 下午 5:03
 **/
@Log
@Component
public class MockQueue {

    @Getter
    private String prePlaceId;

    @Getter
    @Setter
    private String completePlatedId;

    public void setPrePlaceId(String prePlaceId){
        new Thread(() -> {
            log.info("消息队列开始");
            log.info("接收到订单消息:" + prePlaceId);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completePlatedId = prePlaceId;
            log.info("消息队列结束");
        }).start();
    }
}
