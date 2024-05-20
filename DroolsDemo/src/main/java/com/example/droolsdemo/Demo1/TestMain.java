package com.example.droolsdemo.Demo1;

import lombok.SneakyThrows;
import org.kie.api.KieServices;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class TestMain {
    @SneakyThrows
    public static void main(String[] args) {
        // 创建KieServices实例
        KieServices kieServices = KieServices.Factory.get();

        // 加载KieContainer
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        // 获取KieSession
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        // 从KieBase中获取FactType对象
        FactType personType = kieContainer.getKieBase().getFactType("com.example.droolsdemo.Demo1.entity", "Person");

        // 创建一个Person事实对象
        Object person = personType.newInstance();

        // 设置Person对象的属性
        personType.set(person, "name", "John Doe");
        personType.set(person, "age", 30);

        // 插入事实对象到KieSession
        kieSession.insert(person);

        // 执行规则
        kieSession.fireAllRules();

        // 释放资源
        kieSession.dispose();
        
    }
}
