package com.github.sparkzxl.test.infrastructure.config;

import com.github.sparkzxl.test.infrastructure.component.ConfirmCallBackHandler;
import com.github.sparkzxl.test.infrastructure.component.ReturnCallBackHandler;
import com.github.sparkzxl.test.infrastructure.constant.RabbitConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * description: rabbitmq 配置类
 *
 * @author: zhouxinlei
 * @date: 2020-10-31 22:08:49
*/
@Configuration
public class RabbitConfig {

    private final RabbitTemplate rabbitTemplate;

    public RabbitConfig(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 初始化加载方法，对RabbitTemplate进行配置
     *
     * @param
     * @return void
     * @throws
     * @author zhouxinlei
     * @date 2019-12-13 20:50:19
     */
    @PostConstruct
    public void rabbitTemplate() {
        //消息发送确认，发送到交换器Exchange后触发回调
        rabbitTemplate.setConfirmCallback(new ConfirmCallBackHandler());
        //消息发送确认，如果消息从交换器发送到对应队列失败时触发（比如根据发送消息时指定的routingKey找不到队列时会触发）
        rabbitTemplate.setReturnCallback(new ReturnCallBackHandler());
    }

    /**
     * RabbitMq监听容器
     *
     * @param connectionFactory
     * @return SimpleRabbitListenerContainerFactory
     * @throws
     * @author zhouxinlei
     * @date 2019-12-13 20:49:34
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //设置并发
        factory.setConcurrentConsumers(1);
        //最大并发
        factory.setMaxConcurrentConsumers(1);
        //消息接收——手动确认
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置超时
        factory.setReceiveTimeout(2000L);
        //设置重试间隔
        factory.setFailedDeclarationRetryInterval(1000L);
        return factory;
    }

    /**
     * 交换机绑定topic队列
     *
     * @return Binding
     * @author zhouxinlei
     * @time 2019-07-28 04:29
     */
    @Bean
    public Binding topicBinding() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("sparksys.seckill");
    }

    /**
     * topic交换机
     *
     * @param
     * @return TopicExchange
     * @throws
     * @author zhouxinlei
     * @date 2019-12-13 20:51:38
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitConstant.TOPIC_EXCHANGE,true,false);
    }

    /**
     * Topic队列
     *
     * @param
     * @return Queue
     * @throws
     * @author zhouxinlei
     * @date 2019-12-13 20:51:20
     */
    @Bean
    public Queue topicQueue() {
        return new Queue(RabbitConstant.TOPIC_QUEUE);
    }
}
