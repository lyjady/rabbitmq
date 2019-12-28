package com.example.rabbitmq.controller;

import com.example.rabbitmq.consumer.NewsConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author LinYongJin
 * @date 2019/12/29 0:04
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    private static final Logger log = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendMessage")
    public String sendMessage() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend("news_exchange", "news", "news message");
            log.info("消息已发送");
            TimeUnit.SECONDS.sleep(5);
        }
        return "Success";
    }

    @RequestMapping("/test")
    public void test() {
        System.out.println("NewsController.test");
    }
}
