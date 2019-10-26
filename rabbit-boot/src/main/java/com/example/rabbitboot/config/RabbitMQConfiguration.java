package com.example.rabbitboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LinYongJin
 * @date 2019/10/26 16:47
 */
@Configuration
public class RabbitMQConfiguration {

    private static final String EMAIL_QUEUE_NAME = "email_queue";

    private static final String SMS_QUEUE_NAME = "sms_queue";

    public static final String FANOUT_EXCHANGE_NAME = "fanout_exchange";

    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE_NAME);
    }

    @Bean
    public Queue smsQueue() {
        return new Queue(SMS_QUEUE_NAME);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingExchangeEmail(Queue emailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(emailQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeSms(Queue smsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(smsQueue).to(fanoutExchange);
    }
}
