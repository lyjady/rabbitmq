package com.example.rabbitboot.config;

import org.springframework.amqp.core.*;
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

    public static final String DIRECT_EXCHANGE_NAME = "direct_exchange";

    public static final String TOPIC_EXCHANGE_NAME = "topic_exchange";

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
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingExchangeEmail(Queue emailQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(emailQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingExchangeSms(Queue smsQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(smsQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingDirectExchangeEmail(Queue emailQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(emailQueue).to(directExchange).with("log.email");
    }

    @Bean
    public Binding bindingDirectExchangeSms(Queue smsQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(smsQueue).to(directExchange).with("log.sms");
    }

    @Bean
    public Binding bindingTopicExchangeEmail(Queue emailQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(emailQueue).to(topicExchange).with("*.email");
    }

    @Bean
    public Binding bindingTopicExchangeSms(Queue smsQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(smsQueue).to(topicExchange).with("#.sms");
    }
}
