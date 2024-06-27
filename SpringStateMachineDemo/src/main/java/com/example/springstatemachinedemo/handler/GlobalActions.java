package com.example.springstatemachinedemo.handler;

import com.example.springstatemachinedemo.enums.Events;
import com.example.springstatemachinedemo.enums.States;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
public class GlobalActions {
    
    public Action<States, Events> globalAction() {
        return context -> {
            System.out.println("Global action executed during transition from "
                    + context.getSource().getId()
                    + " to "
                    + context.getTarget().getId());
        };
    }
}

