package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.FormService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class FormServiceExample {
    
    @Autowired
    private FormService formService;
    
    public void submitTaskForm(String taskId) {
        // 创建表单数据
        Map<String, String> formProperties = new HashMap<>();
        formProperties.put("approval", "approved");
        
        // 提交任务表单数据
        formService.submitTaskFormData(taskId, formProperties);
        
        
    }
}

