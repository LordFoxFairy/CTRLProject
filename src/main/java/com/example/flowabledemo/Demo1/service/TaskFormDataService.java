package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.FormService;
import org.flowable.engine.form.TaskFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TaskFormDataService {
    
    @Autowired
    private FormService formService;
    
    public TaskFormData getTaskFormData(String taskId) {
        // 获取任务表单数据
        return formService.getTaskFormData(taskId);
    }
}
