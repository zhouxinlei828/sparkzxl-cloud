package com.github.sparkzxl.test.infrastructure.consumer;

import com.github.sparkzxl.test.infrastructure.constant.RabbitConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestConsumer02 {

    @RabbitListener(queues = RabbitConstant.TOPIC_QUEUE)
    public void receiveTopicMessage(Message message, Channel channel) {
        log.info("【TestConsumer02监听到消息】= {}", message);
    }
}
