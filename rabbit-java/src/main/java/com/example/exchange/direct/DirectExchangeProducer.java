package com.example.exchange.direct;

import com.example.utils.CommonUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author LinYongJin
 * @date 2019/10/24 21:05
 */
public class DirectExchangeProducer {

    private static final String EXCHANGE_NAME = "test_direct_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = CommonUtils.getRabbitMQConnection();
        Channel channel = connection.createChannel();
        //声明类型为Direct的交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String routingKey = "log.email";
        for (int i = 0; i < 5; i++) {
            String message = "direct exchange message" + i + "routingKey:" + routingKey;
            //指定routingKey
            channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
        }
        channel.close();
        connection.close();
    }
}
