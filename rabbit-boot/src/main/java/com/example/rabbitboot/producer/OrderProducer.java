package com.example.rabbitboot.producer;

import com.example.rabbitboot.config.RabbitMQConfiguration;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author LinYongJin
 * @date 2019/11/3 16:21
 */
@Component
public class OrderProducer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendOrderMessage(String routingKey) {
        String messageID = UUID.randomUUID().toString().replace("-", "");
        System.out.println(messageID);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        CorrelationData correlationData = new CorrelationData(messageID);
        Message message = MessageBuilder.withBody("iPhone 11 Pro Max Order".getBytes()).setContentEncoding("UTF-8").setMessageId(messageID).build();
        rabbitTemplate.convertAndSend(RabbitMQConfiguration.DIRECT_EXCHANGE_NAME, routingKey, message, correlationData);
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("发送成功, correlationData: " + correlationData.getId());
        } else {
            System.out.println("发送失败, 原因: " + s);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message + ", " + replyText + ", " + replyText + ", " + exchange + ", " + routingKey);
    }
}
