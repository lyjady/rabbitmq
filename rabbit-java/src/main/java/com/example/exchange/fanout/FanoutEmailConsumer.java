package com.example.exchange.fanout;

import com.example.utils.CommonUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author LinYongJin
 * @date 2019/10/20 15:20
 */
public class FanoutEmailConsumer {

    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    private static final String QUEUE_NAME = "email_queue";

    public static void main(String[] args) throws Exception {
        System.out.println("邮件消费者启动");
        Connection connection = CommonUtils.getRabbitMQConnection();
        Channel channel = connection.createChannel();
        //消费者声明队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //队列绑定交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
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
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println(message);
            }
        });
    }
}
