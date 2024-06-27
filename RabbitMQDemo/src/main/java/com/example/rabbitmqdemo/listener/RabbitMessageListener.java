package com.example.rabbitmqdemo.listener;

import com.example.rabbitmqdemo.constant.RabbitConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author liuxy250
 */

@Slf4j
@Component
public class RabbitMessageListener {
    private final Map<String, RabbitListenerContainerFactory<?>> rabbitListenerContainerFactories;

    public RabbitMessageListener(@Qualifier("rabbitListenerContainerFactories") Map<String, RabbitListenerContainerFactory<?>> rabbitListenerContainerFactories) {
        this.rabbitListenerContainerFactories = rabbitListenerContainerFactories;

        log.info("rabbitListenerContainerFactories: {}", this.rabbitListenerContainerFactories);
    }

    @RabbitListener(queues = RabbitConstant.QUEUE_NAME, containerFactory = "instance1RabbitListenerContainerFactory")
    public void handleMessageInstance1(String message) {
        log.info("Instance1 - Received message: {}", message);
    }
}