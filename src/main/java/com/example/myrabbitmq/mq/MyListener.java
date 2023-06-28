package com.example.myrabbitmq.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
public class MyListener {

    private int retryCount = 0;

    @RabbitListener(queues = "test-queue")
    public void receiver(Message message, Channel channel) throws Exception {
        log.info(new String(message.getBody()));
        log.info("retry couunt: {}", retryCount++);
//        int a = 1 / 0;
//        LocalDateTime now = LocalDateTime.now();
//        if (now.getSecond() / 0 != 0) {
//            throw new Exception("error");
//        } else {
//            retryCount = 0;
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        }
    }
}
