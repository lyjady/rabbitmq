package com.example.rabbit.consumer;

import com.example.rabbit.entries.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author LinYongJin
 * @date 2019/11/5 20:44
 */
@Component
public class BootConsumer {

    @RabbitListener(queues = "dead.queue")
    public void receiveDeadMessage(Order order, Message message, Channel channel) throws IOException {
        System.out.println("死信消费者 ");
        System.out.println(order);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
