package com.example.rabbit.controller;

import com.example.rabbit.producer.BootProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LinYongJin
 * @date 2019/11/5 20:46
 */
@RestController
public class MessageController {

    @Autowired
    private BootProducer bootProducer;

    @RequestMapping("/send")
    public String send() {
        bootProducer.sendMessage();
        return "Success";
    }
}
