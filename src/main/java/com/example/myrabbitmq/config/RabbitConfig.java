package com.example.myrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {


    @Bean
    public TopicExchange dlExchange() {
        return new TopicExchange("dl-exchange");
    }

    @Bean
    public Queue dlQueue() {
        return new Queue("dl-queue");
    }

    @Bean
    public Binding dlBinding() {
        return BindingBuilder.bind(dlQueue()).to(dlExchange()).with("dl.#");
    }

    @Bean
    public TopicExchange testExchange() {
        return new TopicExchange("test-exchange");
    }

    @Bean
    public Queue testQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", "dl-exchange");
        arguments.put("x-dead-letter-routing-key", "dl.#");
        return new Queue("test-queue", true, false, false, arguments);
    }

    @Bean
    public Binding testBinding() {
        return BindingBuilder.bind(testQueue()).to(testExchange()).with("test.#");
    }
}
