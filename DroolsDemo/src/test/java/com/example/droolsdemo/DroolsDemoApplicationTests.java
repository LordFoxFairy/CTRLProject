//package com.example.droolsdemo;
//
//import com.example.droolsdemo.DemoX.config.KieSessionConfig;
//import com.example.droolsdemo.DemoX.entity.People;
//import com.example.droolsdemo.DemoX.entity.User;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.kie.api.runtime.KieSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//
//@SpringBootTest
//@Slf4j
//public
//class DroolsDemoApplicationTests {
//
//    @Autowired
//    private KieSession kieSession;
//
//    @Autowired
//    private KieSessionConfig kieSessionConfig;
//
//
//    @Test
//    public void testUserRule() {
//
//        User user = new User("John", 25);
//
//        kieSession.insert(user);
//        int firedRules = kieSession.fireAllRules();
//
//        log.info("{}", firedRules);
//
//    }
//
//    @Test
//    public void testPeopleRule() {
//
//        People people = new People("angle");
//
//        kieSession.insert(people);
//        int firedRules = kieSession.fireAllRules();
//
//        log.info("{}", kieSessionConfig.getLoadedRules());
//
//        log.info("{}", firedRules);
//    }
//}
