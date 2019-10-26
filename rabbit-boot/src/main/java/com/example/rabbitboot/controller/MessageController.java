package com.example.rabbitboot.controller;

import com.example.rabbitboot.producer.BootProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LinYongJin
 * @date 2019/10/26 17:08
 */
@RestController
public class MessageController {

    @Autowired
    private BootProducer bootProducer;

    @RequestMapping("/sendFanout")
    public String sendFanout() {
        bootProducer.sendFanoutMessage();
        return "Success";
    }

    @RequestMapping("/sendDirect")
    public String sendDirect(String routingKey) {
        bootProducer.sendDirectMessage(routingKey);
        return "Success";
    }

    @RequestMapping("/sendTopic")
    public String sendTopic(String routingKey) {
        bootProducer.sendTopicMessage(routingKey);
        return "Success";
    }

    @RequestMapping("/unionPay")
    public String unionPay() {
        return "Success";
    }
}
