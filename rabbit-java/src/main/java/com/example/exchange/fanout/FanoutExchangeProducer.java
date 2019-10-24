package com.example.exchange.fanout;

import com.example.utils.CommonUtils;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author LinYongJin
 * @date 2019/10/20 15:14
 */
public class FanoutExchangeProducer {

    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = CommonUtils.getRabbitMQConnection();
        Channel channel = connection.createChannel();
        //生产者声明交换机(交换机名称,交换机类型)
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        for (int i = 0; i < 5; i++) {
            String message = "exchange-message-" + i;
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        }
        channel.close();
        connection.close();
    }
}
