package com.security.learn.mvc.async;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Date;

/**
 * create by： harry
 * date:  2019/11/24 0024 下午 4:50
 **/
@RestController
@RequestMapping("/order")
@Log
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @GetMapping
    public DeferredResult<String> create(){
       /* Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("副线程开始");

                log.info("创建订单:" + time);
                new Thread().sleep(2000);
                log.info("副线程结束");
                return "success";
            }
        };*/
        log.info("主线程接收到请求");
        long id = new Date().getTime();
        mockQueue.setPrePlaceId(id + "");
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(id +"", result);
        log.info("主线程处理完成请求");
        return result;
    }
}
