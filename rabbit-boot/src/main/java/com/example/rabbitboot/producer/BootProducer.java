package com.example.rabbitboot.producer;

import com.example.rabbitboot.config.RabbitMQConfiguration;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * @author LinYongJin
 * @date 2019/10/26 17:06
 */
@Component
public class BootProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendFanoutMessage() {
        String message = "boot message fanout exchange" + new Date();
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.FANOUT_EXCHANGE_NAME, "", message);
    }

    public void sendDirectMessage(String routingKey) {
        String messageID = UUID.randomUUID().toString().replace("-", "");
        Message message = MessageBuilder.withBody("boot message direct exchange".getBytes()).setContentEncoding("UTF-8").setMessageId(messageID).build();
        System.out.println(messageID);
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.DIRECT_EXCHANGE_NAME, routingKey, message);
    }

    public void sendTopicMessage(String routingKey) {
        String message = "boot message topic exchange" + new Date();
        amqpTemplate.convertAndSend(RabbitMQConfiguration.TOPIC_EXCHANGE_NAME, routingKey, message);
    }
}