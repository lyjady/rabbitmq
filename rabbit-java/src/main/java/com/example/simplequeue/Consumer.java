package com.example.simplequeue;

import com.example.utils.CommonUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author LinYongJin
 * @date 2019/10/19 20:53
 */
public class Consumer {

    private static final String QUEUE_NAME = "rabbit-java";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //1.创建连接
        Connection connection = CommonUtils.getRabbitMQConnection();
        //2.创建通道
        Channel channel = connection.createChannel();
        //3.创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //一次只发给一条消息给消费者
//        channel.basicQos(1);
        //4.监听队列
        while (true) {
            channel.basicConsume(QUEUE_NAME, false, new com.rabbitmq.client.Consumer() {
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
                   try {
                       String message = new String(body, StandardCharsets.UTF_8);
                       System.out.println(message);
//                       Thread.sleep(1000L);
                       //手动签收
                       channel.basicAck(envelope.getDeliveryTag(), false);
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                }
            });
        }
    }
}
