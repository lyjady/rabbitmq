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

    public void sendMessage(String queueName) {
        String message = "boot message fanout exchange" + new Date();
        amqpTemplate.convertAndSend(RabbitMQConfiguration.FANOUT_EXCHANGE_NAME, "", message);
    }
}