package com.example.springstatemachinedemo.handler;

import com.example.springstatemachinedemo.enums.Events;
import com.example.springstatemachinedemo.enums.States;
import org.springframework.messaging.Message;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Component;

@Component
public class StateMachineListener extends StateMachineListenerAdapter<States, Events> {
    
    @Override
    public void stateChanged(State<States, Events> from, State<States, Events> to) {
        System.out.println("State changed from " + (from != null ? from.getId() : "none") + " to " + to.getId());
    }
    
    @Override
    public void eventNotAccepted(Message<Events> event) {
        System.out.println("Event not accepted: " + event.getPayload());
    }
}
