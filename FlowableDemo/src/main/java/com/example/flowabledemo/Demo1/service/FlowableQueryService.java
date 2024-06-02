package com.example.flowabledemo.Demo1.service;

import liquibase.pro.packaged.S;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FlowableQueryService {
    
    @Resource
    private RepositoryService repositoryService;
    
    @Resource
    private TaskService taskService;
    public ProcessDefinition  queryProcessDefinitions() {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .orderByProcessDefinitionKey()
                .asc()
                .list();
        
        for (ProcessDefinition pd : processDefinitions) {
            System.out.println("Found process definition: " + pd.getName() + ", version: " + pd.getVersion());
            System.out.println("Process Definition ID: " + pd.getId());
        }
        
        // 获取第一个流程定义的ID
        if (!processDefinitions.isEmpty()) {
            ProcessDefinition pd = processDefinitions.get(0);
            String processDefinitionId = pd.getId();
            System.out.println("Process Definition ID: " + processDefinitionId);
            return pd;
        }
        
        return null;
    }
    
    public List<Task> getAllTasks() {
        // 查询所有任务
        return taskService.createTaskQuery().list();
    }
}