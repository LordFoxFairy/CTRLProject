package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.RuntimeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class FlowableVariableService {
    
    @Resource
    private RuntimeService runtimeService;
    
    public Map<String, Object> getProcessVariables(String executionId) {
        // 获取流程变量
        return runtimeService.getVariables(executionId);
    }
    
    public void setProcessVariable(String executionId, String variableName, Object value) {
        // 设置流程变量
        runtimeService.setVariable(executionId, variableName, value);
    }
}
