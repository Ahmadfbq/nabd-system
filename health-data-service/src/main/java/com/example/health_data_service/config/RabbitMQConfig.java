package com.example.health_data_service.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String MEASUREMENT_EXCHANGE = "measurement.exchange";
    public static final String MEASUREMENT_QUEUE = "measurement.queue";
    public static final String MEASUREMENT_ROUTING_KEY = "measurement.routing.key";

    @Bean
    public Queue measurementQueue() {
        return new Queue(MEASUREMENT_QUEUE);
    }

    @Bean
    public DirectExchange measurementExchange() {
        return new DirectExchange(MEASUREMENT_EXCHANGE);
    }

    @Bean
    public Binding measurementBinding(Queue measurementQueue, DirectExchange measurementExchange) {
        return BindingBuilder
                .bind(measurementQueue)
                .to(measurementExchange)
                .with(MEASUREMENT_ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
} 