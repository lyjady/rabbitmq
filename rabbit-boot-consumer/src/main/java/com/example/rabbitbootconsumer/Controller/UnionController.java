package com.example.rabbitbootconsumer.Controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LinYongJin
 * @date 2019/10/26 18:02
 */
@RestController
public class UnionController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/send")
    public String send(String queueName) {
        amqpTemplate.convertAndSend("fanout_exchange", "", "boot-message");
        return "Success";
    }
}
