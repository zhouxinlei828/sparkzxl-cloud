package com.sparksys.authority.infrastructure.mq.consumer;

import com.sparksys.authority.infrastructure.mq.message.UserMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * description：
 *
 * @author zhouxinlei
 * @date 2020/6/7 5:14 下午
 */
@Component
@Slf4j
public class UserConsumer {

    @KafkaListener(topics = UserMessage.TOPIC,groupId = "user-consumer-group-"+UserMessage.TOPIC)
    public void onMessage(UserMessage userMessage){
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), userMessage);
    }
}
