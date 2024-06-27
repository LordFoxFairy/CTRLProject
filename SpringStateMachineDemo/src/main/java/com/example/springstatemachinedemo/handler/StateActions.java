//package com.example.springstatemachinedemo.handler;
//
//import com.example.springstatemachinedemo.annotations.StatesOnStateEntry;
//import com.example.springstatemachinedemo.annotations.StatesOnStateExit;
//import com.example.springstatemachinedemo.enums.Events;
//import com.example.springstatemachinedemo.enums.States;
//import org.springframework.statemachine.action.Action;
//import org.springframework.statemachine.annotation.OnStateEntry;
//import org.springframework.statemachine.annotation.OnStateExit;
//import org.springframework.statemachine.annotation.WithStateMachine;
//import org.springframework.stereotype.Component;
//
////@Component
////public class StateActions {
////
////    public Action<States, Events> entryAction() {
////        return context -> {
////            System.out.println("Entering STATE2");
////        };
////    }
////
////    public Action<States, Events> exitAction() {
////        return context -> {
////            System.out.println("Exiting STATE2");
////        };
////    }
////}
//
//
//@Component
//@WithStateMachine(name = "stateActions", id = "myMachineId")
//public class StateActions {
//
//    @StatesOnStateEntry(target = States.STATE2)
//    public void onState2Entry() {
//        // 进入 STATE2 时执行的动作
//        System.out.println("Entering STATE2");
//    }
//
//    @StatesOnStateExit(source = States.STATE2)
//    public void onState2Exit() {
//        // 退出 STATE2 时执行的动作
//        System.out.println("Exiting STATE2");
//    }
//}