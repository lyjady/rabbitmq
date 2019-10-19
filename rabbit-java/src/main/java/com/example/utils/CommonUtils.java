package com.example.utils;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author LinYongJin
 * @date 2019/10/17 21:51
 */
public class CommonUtils {

    public static Connection getRabbitMQConnection() throws IOException, TimeoutException {
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //2.设置主机
        factory.setHost("192.168.0.110");
        //3.设置端口号
        factory.setPort(5672);
        //4.设置用户名
        factory.setUsername("guest");
        //5.设置密码
        factory.setPassword("guest");
        //6.设置虚拟主机
        factory.setVirtualHost("/test");
        return factory.newConnection();
    }
}
