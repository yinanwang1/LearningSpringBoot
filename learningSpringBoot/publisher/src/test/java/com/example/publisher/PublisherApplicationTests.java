package com.example.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class PublisherApplicationTests {

    @Test
    void contextLoads() {
    }

    /// RabbitMQ 测试

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend() {
        rabbitTemplate.convertAndSend("simple.queue", "hello world, simple.queue!");
    }

    @Test
    void testWorkQueue() throws InterruptedException {
        String queueName = "work.queue";
        for (int i =0; i < 50; i++) {
            String msg = "hello world, work.queue! " + i;
            rabbitTemplate.convertAndSend(queueName, msg);
            Thread.sleep(20);
        }
    }

    @Test
    void testSendFanout() {
        String exchangeName = "hmall.fanout";
        String msg = "hello, everyone!";
        rabbitTemplate.convertAndSend(exchangeName, null, msg);

    }

    @Test
    void testSendDirect() {
        String exchangeName = "hmall.direct";
        String msg = "蓝色通知，警告";
        rabbitTemplate.convertAndSend(exchangeName, "blue", msg);
    }

    @Test
    void testSendTopic() {
        String exchangeName = "hmall.topic";
        String msg = "蓝色通知，警告";
        rabbitTemplate.convertAndSend(exchangeName, "china.weather", msg);
    }

    @Test
    void testSendWynDirect() {
        String exchangeName = "wyn.direct.exchange";
        Map<String, Object> msg = new HashMap<>();
        msg.put("name", "jack");
        msg.put("age", 21);
        rabbitTemplate.convertAndSend(exchangeName, "blue", msg);
    }



    /// RabbitMQ 测试 __END__

}
