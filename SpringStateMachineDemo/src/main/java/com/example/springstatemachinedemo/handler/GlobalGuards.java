package com.example.springstatemachinedemo.handler;

import com.example.springstatemachinedemo.enums.Events;
import com.example.springstatemachinedemo.enums.States;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

@Component
public class GlobalGuards {
    
    public Guard<States, Events> globalGuard() {
        return context -> {
            System.out.println("Global guard condition checked!");
            return true; // 可以根据业务逻辑返回 true 或 false
        };
    }
}

