//package com.example.springstatemachinedemo;
//
//import com.example.springstatemachinedemo.enums.Events;
//import com.example.springstatemachinedemo.enums.States;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.statemachine.StateMachine;
//import org.springframework.statemachine.config.StateMachineFactory;
//import org.springframework.statemachine.support.DefaultStateMachineContext;
//import reactor.core.publisher.Mono;
//
//@SpringBootTest
//@Slf4j
//class SpringStateMachineDemoApplicationTests {
////
////	@Autowired
////	private StateMachineFactory<String, String> stateMachineFactory;
////
////	@Test
////	public void testStateMachineTransitions() {
////		// 获取状态机实例
////		StateMachine<String, String> stateMachine = stateMachineFactory.getStateMachine();
////
////		// 重置状态机的初始状态
////		stateMachine.getStateMachineAccessor()
////				.doWithAllRegions(access -> access.resetStateMachineReactively(
////						new DefaultStateMachineContext<>("STATE1", null, null, null)).block());
////
////		// 启动状态机
////		stateMachine.startReactively().block();
////		log.info("Initial state: {}", getStateName(stateMachine));
////
////		// 发送第一个事件并检查状态转换
////		sendEvent(stateMachine, "EVENT1");
////		log.info("State after EVENT1: {}", getStateName(stateMachine));
////
////		// 发送第二个事件并检查状态转换
////		sendEvent(stateMachine, "EVENT2");
////		log.info("State after EVENT2: {}", getStateName(stateMachine));
////
////		// 停止状态机
////		stateMachine.stopReactively().block();
////	}
////
////	private void sendEvent(StateMachine<String, String> stateMachine, String event) {
////		Message<String> message = MessageBuilder.withPayload(event).build();
////		stateMachine.sendEvent(Mono.just(message)).blockFirst();
////	}
////
////	private String getStateName(StateMachine<String, String> stateMachine) {
////		return stateMachine.getState() != null ? stateMachine.getState().getId() : "UNKNOWN";
////	}
////
//
////	// 注入状态机工厂
////	@Autowired
////	private StateMachineFactory<States, Events> stateMachineFactory;
////
////	@Test
////	public void testStateMachineTransitions() {
////		// 获取状态机实例
////		StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine();
////
////		// 重置状态机的初始状态
////		stateMachine.getStateMachineAccessor()
////				.doWithAllRegions(access -> access.resetStateMachineReactively(
////						new DefaultStateMachineContext<>(States.STATE1, null, null, null)).block());
////
////		// 启动状态机
////		stateMachine.startReactively().block();
////		log.info("Initial state: {}", getStateName(stateMachine));
////
////		// 发送第一个事件并检查状态转换
////		sendEvent(stateMachine, Events.EVENT1);
////		log.info("State after EVENT1: {}", getStateName(stateMachine));
////
////		// 发送第二个事件并检查状态转换
////		sendEvent(stateMachine, Events.EVENT2);
////		log.info("State after EVENT2: {}", getStateName(stateMachine));
////
////		// 发送第三个事件并检查状态转换
////		sendEvent(stateMachine, Events.EVENT3);
////		log.info("State after EVENT3: {}", getStateName(stateMachine));
////
////		// 停止状态机
////		stateMachine.stopReactively().block();
////	}
////
////	private void sendEvent(StateMachine<States, Events> stateMachine, Events event) {
////		Message<Events> message = MessageBuilder.withPayload(event).build();
////		stateMachine.sendEvent(Mono.just(message)).blockFirst();
////	}
////
////	private States getStateName(StateMachine<States, Events> stateMachine) {
////		return stateMachine.getState() != null ? stateMachine.getState().getId() : States.STATE3;
////	}
////
////
////
////	@Autowired
////	private StateMachine<States, Events> stateMachine;
////
////	@Test
////	public void testStateMachineTransitions() {
////		// 启动状态机
////		stateMachine.startReactively().block();
////		log.info("Initial state: {}", getStateName(stateMachine));
////
////		// 发送第一个事件并检查状态转换
////		sendEvent(stateMachine, Events.EVENT1);
////		log.info("State after EVENT1: {}", getStateName(stateMachine));
////
////		// 发送第二个事件并检查状态转换
////		sendEvent(stateMachine, Events.EVENT2);
////		log.info("State after EVENT2: {}", getStateName(stateMachine));
////
////		// 发送第三个事件并检查状态转换
////		sendEvent(stateMachine, Events.EVENT3);
////		log.info("State after EVENT3: {}", getStateName(stateMachine));
////
////		// 停止状态机
////		stateMachine.stopReactively().block();
////	}
//
////	private void sendEvent(StateMachine<States, Events> stateMachine, Events event) {
////		Message<Events> message = MessageBuilder.withPayload(event).build();
////		stateMachine.sendEvent(Mono.just(message)).blockFirst();
////	}
////
////	private States getStateName(StateMachine<States, Events> stateMachine) {
////		return stateMachine.getState() != null ? stateMachine.getState().getId() : States.STATE3;
////	}
//
//
//
//	@Autowired
//	private StateMachineFactory<States, Events> stateMachineFactory;
//
//	@Test
//	public void testStateMachineTransitions() {
//
//		StateMachine<States, Events> stateMachine = stateMachineFactory.getStateMachine();
//
//		// 启动状态机
//		stateMachine.startReactively().block();
//		System.out.println("Initial state: " + getStateName(stateMachine));
//
//		// 发送第一个事件并检查状态转换
//		sendEvent(stateMachine, Events.EVENT1);
//		System.out.println("State after EVENT1: " + getStateName(stateMachine));
//
//		// 发送第二个事件并检查状态转换
//		sendEvent(stateMachine, Events.EVENT2);
//		System.out.println("State after EVENT2: " + getStateName(stateMachine));
//
//		// 停止状态机
//		stateMachine.stopReactively().block();
//	}
//
//	private void sendEvent(StateMachine<States, Events> stateMachine, Events event) {
//		Message<Events> message = MessageBuilder.withPayload(event).build();
//		stateMachine.sendEvent(Mono.just(message)).blockFirst();
//	}
//
//	private States getStateName(StateMachine<States, Events> stateMachine) {
//		return stateMachine.getState() != null ? stateMachine.getState().getId() : null;
//	}
//
//
//
//}
