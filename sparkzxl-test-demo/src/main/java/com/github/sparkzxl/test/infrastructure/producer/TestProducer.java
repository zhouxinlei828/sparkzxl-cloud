package com.github.sparkzxl.test.infrastructure.producer;

import com.alibaba.fastjson.JSON;
import com.github.sparkzxl.test.infrastructure.constant.RabbitConstant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestProducer {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public TestProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 第一个参数：TopicExchange名字
     * 第二个参数：Route-Key
     * 第三个参数：要发送的内容
     *
     * @param message 消息
     */
    public void sendMessage(String message) {
        this.rabbitTemplate.convertAndSend(RabbitConstant.TOPIC_EXCHANGE, "sparksys.seckill", message);
    }
}
