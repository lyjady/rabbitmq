package com.example.rabbitbootconsumer.consumer;

import com.example.rabbitbootconsumer.utils.HttpClientUtil;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author LinYongJin
 * @date 2019/10/26 17:14
 */
@Component
public class BootConsumer {

    @RabbitListener(queues = "email_queue")
    public void consumerEmailMessage(Message message, @Headers Map<String, Object> headers, Channel channel) throws Exception {
        try {
            String messageID = message.getMessageProperties().getMessageId();
            System.out.println("邮件消费者: " + message + ", messageID: " + messageID);
            int i = 2 / 0;
            //手动ack
            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            //手动签收
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            e.printStackTrace();
            //丢弃消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        }
    }

    @RabbitListener(queues = "sms_queue")
    public void consumerSmsMessage(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        System.out.println("短信消费者: " + message);
        //手动ack
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手动签收
        channel.basicAck(deliveryTag, false);
    }

    @RabbitListener(queues = "dead_queue")
    public void consumerDeadMessage(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        System.out.println("死信消费者: " + message);
        //手动ack
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手动签收
        channel.basicAck(deliveryTag, false);
    }

    @RabbitListener(queues = "order_queue")
    public void consumerOrderMessage(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {
        System.out.println("订单消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
        //手动ack
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //手动签收
        channel.basicAck(deliveryTag, false);
    }
}
