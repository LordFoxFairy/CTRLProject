package com.example.springstatemachinedemo;

import com.example.springstatemachinedemo.enums.Events;
import com.example.springstatemachinedemo.enums.States;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import reactor.core.publisher.Mono;

@SpringBootTest
@Slf4j
public class StateMachineTest {
    
    
    @Autowired
    private StateMachineFactory<States, Events> stateMachineFactory;
    
    private StateMachine<States, Events> stateMachine;
    
    @Test
    public void testStateMachineTransitions() {
        // 获取状态机实例
        stateMachine = stateMachineFactory.getStateMachine();
        
        stateMachine.start();
        
        // Send events B to G
        sendEventAndPrintState(Events.B);
        sendEventAndPrintState(Events.G);
    }
    
    private void sendEventAndPrintState(Events event) {
        stateMachine.sendEvent(event);
        System.out.println("State after Event " + event + ": " + stateMachine.getState().getId());
    }
}
