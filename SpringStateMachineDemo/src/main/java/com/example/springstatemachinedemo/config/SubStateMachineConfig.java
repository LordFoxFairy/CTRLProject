//package com.example.springstatemachinedemo.config;
//
//import com.example.springstatemachinedemo.enums.Events;
//import com.example.springstatemachinedemo.enums.SubStates;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.statemachine.config.EnableStateMachineFactory;
//import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
//import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
//import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
//
//@Configuration
//@EnableStateMachineFactory(name = "subStateMachineFactory")
//public class SubStateMachineConfig extends StateMachineConfigurerAdapter<SubStates, Events> {
//
//    @Override
//    public void configure(StateMachineStateConfigurer<SubStates, Events> states) throws Exception {
//        states
//                .withStates()
//                .initial(SubStates.SUB_STATE1)
//                .state(SubStates.SUB_STATE2);
//    }
//
//    @Override
//    public void configure(StateMachineTransitionConfigurer<SubStates, Events> transitions) throws Exception {
//        transitions
//                .withExternal()
//                .source(SubStates.SUB_STATE1).target(SubStates.SUB_STATE2).event(Events.EVENT3)
//                .and()
//                .withExternal()
//                .source(SubStates.SUB_STATE2).target(SubStates.SUB_STATE1).event(Events.EVENT4);
//    }
//}
//
