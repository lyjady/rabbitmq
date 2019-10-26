package com.example.rabbitboot.producer;

import com.example.rabbitboot.config.RabbitMQConfiguration;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author LinYongJin
 * @date 2019/10/26 17:06
 */
@Component
public class BootProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendFanoutMessage() {
        String message = "boot message fanout exchange" + new Date();
        amqpTemplate.convertAndSend(RabbitMQConfiguration.FANOUT_EXCHANGE_NAME, "", message);
    }

    public void sendDirectMessage(String routingKey) {
        String message = "boot message direct exchange" + new Date();
        amqpTemplate.convertAndSend(RabbitMQConfiguration.DIRECT_EXCHANGE_NAME, routingKey, message);
    }

    public void sendTopicMessage(String routingKey) {
        String message = "boot message topic exchange" + new Date();
        amqpTemplate.convertAndSend(RabbitMQConfiguration.TOPIC_EXCHANGE_NAME, routingKey, message);
    }
}