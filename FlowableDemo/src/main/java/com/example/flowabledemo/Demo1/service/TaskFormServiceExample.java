package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.FormService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class TaskFormServiceExample {
    
    @Autowired
    private FormService formService;
    
    @Autowired
    private TaskService taskService;
    
    public void submitTaskForm(String taskId) {
        // 打印调试信息
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        System.out.println("Submitting form for task: " + task);
        
        // 创建表单数据
        Map<String, String> formProperties = new HashMap<>();
        formProperties.put("approval", "approved");
        
        System.out.println("Form properties: " + formProperties);
        System.out.println("Form service: " + formService);
        
        // 提交任务表单数据
        try {
            System.out.println("formService =" + formService);
            formService.submitTaskFormData(taskId, formProperties);
            System.out.println("Form submitted successfully.");
        } catch (Exception e) {
            System.err.println("Error submitting form: " + e.getMessage());
        }
        
        // 打印提交后任务状态
        Task updatedTask = taskService.createTaskQuery().taskId(taskId).singleResult();
        System.out.println("Task after form submission: " + updatedTask);
    }
}
