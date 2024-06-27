package com.example.rabbitmqdemo.controller;

import com.example.rabbitmqdemo.handler.RabbitMessageSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RabbitMQController {
    
    @Resource
    private RabbitMessageSender messageSender;
    
    @GetMapping("/send")
    public void sendMessage(){
        messageSender.sendDefaultMessage("Hello World");
    }
    
}
