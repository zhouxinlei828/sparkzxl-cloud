package com.github.sparkzxl.test.infrastructure.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * description: 消息发送到交换器Exchange回调处理类
 *
 * @author: fin-9062
 * @date: 2020-10-31 22:12:22
*/
@Slf4j
public class ConfirmCallBackHandler implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息唯一标识：{}", correlationData);
        log.info("确认结果：{}", ack);
        log.info("失败原因：{}", cause);
    }
}
