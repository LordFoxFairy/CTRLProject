package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.FormService;
import org.flowable.engine.form.StartFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StartFormDataService {
    
    @Autowired
    private FormService formService;
    
    public StartFormData getStartFormData(String processDefinitionId) {
        // 获取启动表单数据
        return formService.getStartFormData(processDefinitionId);
    }
}
