package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FlowableCompletionService {
    
    @Autowired
    private TaskService taskService;
    
    public void completeTask(String taskId) {
        // 完成任务
        taskService.complete(taskId);
    }
}
