package com.example.rabbitmqdemo.handler;

import com.example.rabbitmqdemo.config.RabbitMqProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author liuxy250
 */

@Slf4j
@Component
@EnableConfigurationProperties(RabbitMqProperties.class)
public class RabbitMessageSender {

    private final Map<String, RabbitTemplate> rabbitTemplates;

    private final RabbitMqProperties rabbitMqProperties;

    @Autowired
    public RabbitMessageSender(@Qualifier("rabbitTemplates") Map<String, RabbitTemplate> rabbitTemplates, RabbitMqProperties rabbitMqProperties) {
        this.rabbitTemplates = rabbitTemplates;
        this.rabbitMqProperties = rabbitMqProperties;
    }

    public void sendMessage(String instance, String exchange, String routingKey, String message) {
        RabbitTemplate rabbitTemplate = rabbitTemplates.get(instance);
        if (rabbitTemplate != null) {
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            log.info("Sent message: {} to exchange: {} with routing key: {} on instance: {}", message, exchange, routingKey, instance);
        } else {
            log.error("RabbitTemplate for instance: {} not found", instance);
        }
    }

    public void sendMessage(String instance, String message) {
        RabbitMqProperties.RabbitProperties rabbitProperties = rabbitMqProperties.getInstances().get(instance);
        this.sendMessage(instance, rabbitProperties.getExchange(), rabbitProperties.getRoutingKey(), message);
    }
    
    public void sendDefaultMessage(String message) {
        String instance = "instance1";
        RabbitMqProperties.RabbitProperties rabbitProperties = rabbitMqProperties.getInstances().get(instance);
        this.sendMessage(instance, rabbitProperties.getExchange(), rabbitProperties.getRoutingKey(), message);
    }
}
