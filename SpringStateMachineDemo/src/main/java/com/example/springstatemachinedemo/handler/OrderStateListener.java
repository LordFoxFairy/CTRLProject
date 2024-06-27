package com.example.springstatemachinedemo.handler;

import com.example.springstatemachinedemo.enums.OrderEvent;
import com.example.springstatemachinedemo.enums.OrderState;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

public class OrderStateListener extends StateMachineListenerAdapter<OrderState, OrderEvent> {
    
    @Override
    public void stateChanged(State<OrderState, OrderEvent> from, State<OrderState, OrderEvent> to) {
        if (from == null) {
            System.out.println("Entering state: " + to.getId());
        } else if (to == null) {
            System.out.println("Leaving state: " + from.getId());
        } else {
            System.out.println("Transitioning from " + from.getId() + " to " + to.getId());
        }
    }
}

