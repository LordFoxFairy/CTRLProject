package com.example.droolsdemo;

import com.example.droolsdemo.Demo1.entity.Fact;
import com.example.droolsdemo.Demo1.entity.Property;
import com.example.droolsdemo.Demo1.entity.RuleEngineFact;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoTestMain1{
    
    @Autowired
    private KieContainer kieContainer;
//
//    @SneakyThrows
//    @Test
//    public void test1(){
//        KieSession kieSession = kieContainer.newKieSession();
//        Person person = new Person();
//        person.setName("John Doe");
//        person.setAge(30);
//
//        kieSession.insert(person);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//    }
//
//
//    @Test
//    public void test2(){
//        List<Order> orderList = createOrderList();
//        for (Order order : orderList) {
//            if (order.getAmount() <= 100) {
//                order.setScore(0);
//                addScore(order);
//            } else if (order.getAmount() <= 500) {
//                order.setScore(100);
//                addScore(order);
//            } else if (order.getAmount() <= 1000) {
//                order.setScore(500);
//                addScore(order);
//            } else {
//                order.setScore(1000);
//                addScore(order);
//            }
//        }
//    }
//
//    @Test
//    public void test3(){
//        KieSession kieSession = kieContainer.newKieSession();
//        List<Order> orderList = createOrderList();
//        for (Order order: orderList) {
//            // 1-规则引擎处理逻辑
//            kieSession.insert(order);
//            kieSession.fireAllRules();
//            // 2-执行完规则后, 执行相关的逻辑
//            addScore(order);
//        }
//        kieSession.dispose();
//    }
//
//
//    private static void addScore(Order o){
//        System.out.println("用户" + o.getUser().getName() + "享受额外增加积分: " + o.getScore());
//    }
//
//    public static List<Order> createOrderList() {
//        List<Order> orderList = new ArrayList<>();
//        String formatTime = "yyyy-MM-dd";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatTime);
//
//        orderList.add(createOrder(80, "2015-07-01", formatter, 1, "Name1", 111));
//        orderList.add(createOrder(200, "2015-07-02", formatter, 2, "Name2", 0));
//        orderList.add(createOrder(800, "2015-07-03", formatter, 3, "Name3", 0));
//        orderList.add(createOrder(1500, "2015-07-04", formatter, 4, "Name4", 0));
//
//        return orderList;
//    }
//
//    private static Order createOrder(int amount, String bookingDateStr, DateTimeFormatter formatter, int userLevel, String userName, int score) {
//        Order order = new Order();
//        order.setAmount(amount);
//        LocalDate date = LocalDate.parse(bookingDateStr, formatter);
//        LocalDateTime bookingDateTime = date.atStartOfDay(); // 使用开始时间
//        order.setBookingDate(bookingDateTime);
//
//        User user = new User();
//        user.setLevel(userLevel);
//        user.setName(userName);
//        order.setUser(user);
//
//        order.setScore(score);
//        return order;
//    }
//
//    @Test
//    public void test4(){
//        KieSession kieSession = kieContainer.newKieSession();
//
//        // 创建事实对象
//        Fact fact = new Fact();
//        fact.setAge(20);
//        fact.setName("胡桃1");
//
//        kieSession.insert(fact);
//        kieSession.fireAllRules();
//
//    }
//
    @SneakyThrows
    @Test
    public void test5(){
        
        
        KieSession kieSession = kieContainer.newKieSession();
        
//        PersonFact person = new PersonFact();
//        person.setName("李某某");
//        person.setBirthday(DateUtils.parseDate("1999-01-01","yyyy-MM-dd"));
//
//        kieSession.insert(person);

        Fact fact = new Fact();
        fact.setAge(20);
        fact.setName("1");

        kieSession.insert(fact);
        
        kieSession.startProcess("process.simpleRuleFlow");
        kieSession.fireAllRules();
        kieSession.dispose();
    }
    
    @Test
    public void test6(){
        KieSession ksession = kieContainer.newKieSession();
        
        // 创建一个存储规则参数的 Map
        Map<String, Property> variables = new HashMap<>();
        
        // 添加温度属性
        Property temperature = new Property("between", true,20, 30); // 范围条件
        variables.put("温度", temperature);
        
        // 添加湿度属性
        Property humidity = new Property(50, "<=");
        variables.put("湿度", humidity);
        
        // 将规则参数传递给规则
        ksession.setGlobal("variables", variables);
        
        // 插入一个 RuleEngineFact 对象，可能会触发规则
        RuleEngineFact fact1 = new RuleEngineFact("温度", 125); // 设置温度值为 25
        ksession.insert(fact1);
        
        
        // 插入一个 RuleEngineFact 对象，可能会触发规则
        RuleEngineFact fact2 = new RuleEngineFact("湿度", 25); // 设置温度值为 25
        ksession.insert(fact2);
        
        // 执行规则
        ksession.fireAllRules();
        ksession.dispose();
    }
}
