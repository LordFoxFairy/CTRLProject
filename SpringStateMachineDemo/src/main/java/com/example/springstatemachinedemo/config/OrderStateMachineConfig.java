package com.example.springstatemachinedemo.config;

import com.example.springstatemachinedemo.enums.OrderEvent;
import com.example.springstatemachinedemo.enums.OrderState;
import com.example.springstatemachinedemo.handler.OrderStateListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {
    
    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderState.PAYING)
                .states(EnumSet.allOf(OrderState.class))
                .and()
                .withStates()
                .parent(OrderState.PAYING)
                .initial(OrderState.PAID)
                .state(OrderState.PAID)
                .and()
                .withStates()
                .parent(OrderState.PAID)
                .initial(OrderState.SHIPPING)
                .state(OrderState.SHIPPING)
                .state(OrderState.SHIPPED);
    }
    
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderState.PAYING).target(OrderState.PAID).event(OrderEvent.PAY)
                .and()
                .withExternal()
                .source(OrderState.PAID).target(OrderState.SHIPPING).event(OrderEvent.SHIP)
                .and()
                .withExternal()
                .source(OrderState.SHIPPING).target(OrderState.SHIPPED).event(OrderEvent.DELIVER)
                .and()
                .withExternal()
                .source(OrderState.SHIPPED).target(OrderState.CANCELLED).event(OrderEvent.CANCEL);
    }
    
    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderState, OrderEvent> config) throws Exception {
        config
                .withConfiguration()
                .listener(stateListener());
    }
    
    @Bean
    public OrderStateListener stateListener() {
        return new OrderStateListener();
    }
}

