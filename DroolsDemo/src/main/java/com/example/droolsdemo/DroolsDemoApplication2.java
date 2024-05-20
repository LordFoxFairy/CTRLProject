//package com.example.droolsdemo;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
////@EnableScheduling //开启定时任务
//@Slf4j
//public class DroolsDemoApplication2 {
//
////    @Resource
////    private KieSession kieSession;
//
//    public static void main(String[] args) {
//        SpringApplication.run(DroolsDemoApplication2.class, args);
//    }
//
////    @SneakyThrows
////    @Override
////    public void run(String... args) {
////        new Thread(new Runnable() {
////            public void run() {
////                //启动规则引擎进行规则匹配，直到调用halt方法才结束规则引擎
////                kieSession.fireUntilHalt();
////            }
////        }).start();
////    }
//
////    @PreDestroy
////    public void closeKieSession() {
////        if (kieSession != null) {
////            kieSession.halt();
////            kieSession.dispose();
////        }
////    }
//}
