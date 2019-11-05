package com.example.rabbit.producer;

import com.example.rabbit.config.RabbitMQConfig;
import com.example.rabbit.entries.Order;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LinYongJin
 * @date 2019/11/5 20:35
 */
@Component
public class BootProducer implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        rabbitTemplate.setConfirmCallback(this);
        Order order = new Order();
        order.setStatus(1);
        order.setName("iPhone 11 Max Pro");
        rabbitTemplate.convertAndSend(RabbitMQConfig.BOOT_EXCHANGE_QUEUE, "boot", order, message -> {
            MessageProperties properties = message.getMessageProperties();
            properties.setExpiration("30000");
            properties.setContentEncoding("utf-8");
            return message;
        });
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("投递成功!!!!!");
        }
    }
}
