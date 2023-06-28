package com.example.myrabbitmq.controller;

import jakarta.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/mq")
public class MqController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/send")
    public void send() {
        LocalDateTime now = LocalDateTime.now();
        boolean result = rabbitTemplate.invoke(operations -> {
                    rabbitTemplate.convertAndSend("test-exchange", "test.msg", now.toString());
                    return rabbitTemplate.waitForConfirms(30000);
                });
        if (!result) {
            log.info("Send message failed");
        } else {
            log.info("Send message successfully");
        }
    }
}
