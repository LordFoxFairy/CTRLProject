package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class FlowableRuntimeService {
    
    @Resource
    private RuntimeService runtimeService;
    
    public ProcessInstance startProcessInstance(String processDefinitionKey) {
        // 启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        
        // 输出流程实例信息
        System.out.println("Process Instance ID: " + processInstance.getId());
        System.out.println("Process Definition ID: " + processInstance.getProcessDefinitionId());
        
        return processInstance;
    }
    
    
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey) {
        // 启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
        
        // 输出流程实例信息
        System.out.println("Process Instance ID: " + processInstance.getId());
        System.out.println("Process Definition ID: " + processInstance.getProcessDefinitionId());
        
        return processInstance;
    }
    public void printProcessInstanceInfo(ProcessInstance processInstance) {
        System.out.println("Process Instance ID: " + processInstance.getId());
        System.out.println("Process Definition ID: " + processInstance.getProcessDefinitionId());
        System.out.println("Business Key: " + processInstance.getBusinessKey());
        System.out.println("processVariables: " + processInstance.getProcessVariables());
    }
    
    
    public <K, V> ProcessInstance   startProcessInstance(String processDefinitionKey, Map<String, Object> kvMap) {
        // 启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, kvMap);
        
        // 输出流程实例信息
        System.out.println("Process Instance ID: " + processInstance.getId());
        System.out.println("Process Definition ID: " + processInstance.getProcessDefinitionId());
        
        return processInstance;
    }
}
