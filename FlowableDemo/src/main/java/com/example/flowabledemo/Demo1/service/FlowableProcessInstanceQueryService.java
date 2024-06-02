package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FlowableProcessInstanceQueryService {
    
    @Resource
    private RuntimeService runtimeService;
    
    public void queryProcessInstances(String processDefinitionKey) {
        // 创建查询对象
        List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(processDefinitionKey) // 设置查询条件
                .list(); // 执行查询
        
        // 处理查询结果
        for (ProcessInstance processInstance : processInstances) {
            System.out.println("Process Instance ID: " + processInstance.getId());
            System.out.println("Process Definition ID: " + processInstance.getProcessDefinitionId());
            System.out.println("Process Instance Start Time: " + processInstance.getStartTime());
        }
    }
    
    public void  getProcessInstanceByBusinessKey(String businessKey){
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
        System.out.println("processInstance：" + processInstance);
    }
    
}
