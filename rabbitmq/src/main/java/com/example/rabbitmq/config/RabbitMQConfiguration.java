package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LinYongJin
 * @date 2019/12/29 0:02
 */
@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Queue newsQueue() {
        return new Queue("news_queue", true);
    }

    @Bean
    public DirectExchange newsExchange() {
        return new DirectExchange("news_exchange");
    }

    @Bean
    public Binding bindingDeadExchange(Queue newsQueue, DirectExchange newsExchange) {
        return BindingBuilder.bind(newsQueue).to(newsExchange).with("news");
    }
}
