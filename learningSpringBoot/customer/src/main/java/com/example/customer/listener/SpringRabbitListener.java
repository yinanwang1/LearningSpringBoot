package com.example.customer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * SpringRabbitListener
 *
 * @author arthurwang
 * @version 1.0
 * 2024/12/30 15:19
 **/
@Slf4j
@Component
public class SpringRabbitListener {
    // 其中这个会在QA的MQ中添加交换机和队列
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "wyn.direct.queue1", durable = "true"),
//            exchange = @Exchange(name = "wyn.direct.exchange", type = ExchangeTypes.DIRECT),
//            key = {"blue", "red"}
//    ))
//    public void onMessage(Map<String, Object> msg) {
//        log.info("SpringRabbitListener receive msg: {}", msg);
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "wyn.direct.queue2", durable = "true"),
//            exchange = @Exchange(name = "wyn.direct.exchange", type = ExchangeTypes.DIRECT),
//            key = {"yellow", "red"}
//    ))
//    public void onMessage2(Map<String, Object> msg) {
//        log.info("SpringRabbitListener receive2 msg: {}", msg);
//    }

}
