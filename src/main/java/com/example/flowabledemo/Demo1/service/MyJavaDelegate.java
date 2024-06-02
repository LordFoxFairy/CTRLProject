package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class MyJavaDelegate implements JavaDelegate {
    
    @Override
    public void execute(DelegateExecution execution) {
        // 获取流程变量
        String processVariable = (String) execution.getVariable("myVariable");
        System.out.println("Process Variable: " + processVariable);
        
        // 执行业务逻辑
        System.out.println("Executing custom business logic.");
        
        // 设置新的流程变量
        execution.setVariable("newVariable", "newValue");
    }
}

