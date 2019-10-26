package com.example.rabbitbootconsumer.consumer;

import com.example.rabbitbootconsumer.utils.HttpClientUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author LinYongJin
 * @date 2019/10/26 17:14
 */
@Component
public class BootConsumer {

    @RabbitListener(queues = "email_queue")
    public void consumerEmailMessage(String message) throws Exception {
        System.out.println("邮件消费者: " + message);

    }

    @RabbitListener(queues = "sms_queue")
    public void consumerSmsMessage(String message) {
        System.out.println("短信消费者: " + message);
    }
}
