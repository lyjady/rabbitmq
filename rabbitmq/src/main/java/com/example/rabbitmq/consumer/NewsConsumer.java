package com.example.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author LinYongJin
 * @date 2019/12/29 0:07
 */
@Component
public class NewsConsumer {

    private static final Logger log = LoggerFactory.getLogger(NewsConsumer.class);

    @RabbitListener(queues = "news_queue")
    public void receiveMessage(Message message) {
        log.info(new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
