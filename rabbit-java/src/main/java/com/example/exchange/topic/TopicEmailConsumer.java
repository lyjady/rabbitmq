package com.example.exchange.topic;

import com.example.utils.CommonUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author LinYongJin
 * @date 2019/10/24 21:12
 */
public class TopicEmailConsumer {

    private static final String QUEUE_NAME = "topic_email_queue";

    private static final String EXCHANGE_NAME = "test_topic_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("邮件消费者启动");
        Connection connection = CommonUtils.getRabbitMQConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //队列绑定交换机并指定routingKey
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "#.email");
        channel.basicConsume(QUEUE_NAME, true, new Consumer() {
            @Override
            public void handleConsumeOk(String consumerTag) {

            }

            @Override
            public void handleCancelOk(String consumerTag) {

            }

            @Override
            public void handleCancel(String consumerTag) throws IOException {

            }

            @Override
            public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {

            }

            @Override
            public void handleRecoverOk(String consumerTag) {

            }

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("收到的消息: " + new String(body, StandardCharsets.UTF_8));
            }
        });
    }
}
