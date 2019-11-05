package com.example.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LinYongJin
 * @date 2019/11/5 20:26
 */
@Configuration
public class RabbitMQConfig {

    public static final String BOOT_QUEUE_NAME = "boot.queue";

    public static final String BOOT_EXCHANGE_QUEUE = "boot.exchange";

    public  static final String DEAD_QUEUE_NAME = "dead.queue";

    public static final String DEAD_EXCHANGE_NAME = "dead.exchange";

    @Bean
    public Queue bootQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", DEAD_EXCHANGE_NAME);
        args.put("x-dead-letter-routing-key", "dead");
        return new Queue(BOOT_QUEUE_NAME, true, false, false, args);
    }

    @Bean
    public Queue deadQueue() {
        return new Queue(DEAD_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange bootExchange() {
        return new DirectExchange(BOOT_EXCHANGE_QUEUE);
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(DEAD_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingDeadExchange(Queue deadQueue, DirectExchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with("dead");
    }

    @Bean
    public Binding bindingBootExchange(Queue bootQueue, DirectExchange bootExchange) {
        return BindingBuilder.bind(bootQueue).to(bootExchange).with("boot");
    }
}
