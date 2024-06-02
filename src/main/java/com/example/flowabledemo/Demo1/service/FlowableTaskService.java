package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FlowableTaskService {
    
    @Resource
    private TaskService taskService;
    
    public Task createTask(String name, String description, String assignee) {
        // 创建一个新的任务
        Task task = taskService.newTask();
        task.setName(name);
        task.setDescription(description);
        task.setAssignee(assignee);
        task.setFormKey("approveForm");
        taskService.saveTask(task);
        return task;
    }
}
