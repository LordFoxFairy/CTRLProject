package com.example.springstatemachinedemo;

import com.example.springstatemachinedemo.enums.OrderEvent;
import com.example.springstatemachinedemo.enums.OrderState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

@SpringBootTest
public class OrderStateMachineTest {
    
    @Autowired
    private StateMachine<OrderState, OrderEvent> stateMachine;
    
    @Test
    public void testStateTransitions() {
        // 启动状态机
        stateMachine.start();
        System.out.println("============================================================");
        
        // 发送支付事件
        stateMachine.sendEvent(OrderEvent.PAY);
        getCurrentState();
        
        // 发送发货事件
        stateMachine.sendEvent(OrderEvent.SHIP);
        getCurrentState();

        // 发送交付事件
        stateMachine.sendEvent(OrderEvent.DELIVER);
        getCurrentState();

        // 发送取消事件
        stateMachine.sendEvent(OrderEvent.CANCEL);
        getCurrentState();
    }
    
    private void getCurrentState() {
        System.out.println("============================================================");
        System.out.println("current state: " + stateMachine.getState().getId());
        System.out.println("============================================================");
    }
}

