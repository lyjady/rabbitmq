package com.example.simplequeue;

import com.example.utils.CommonUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.TimeoutException;

/**
 * @author LinYongJin
 * @date 2019/10/19 20:23
 */
public class Producer {

    private static final String QUEUE_NAME = "rabbit-java";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.创建连接
        Connection connection = CommonUtils.getRabbitMQConnection();
        //2.创建通达
        Channel channel = connection.createChannel();
        //3.创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 5; i++) {
            String message = "simple-queue-message" + i;
            //4.发送消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        }
        //5.关闭连接
        channel.close();
        connection.close();
    }
}
