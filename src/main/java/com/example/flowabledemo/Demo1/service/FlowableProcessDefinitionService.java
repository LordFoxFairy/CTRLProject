package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FlowableProcessDefinitionService {
    
    @Resource
    private RepositoryService repositoryService;
    
    public void queryProcessDefinitions() {
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .orderByProcessDefinitionKey()
                .asc()
                .list();
        
        for (ProcessDefinition pd : processDefinitions) {
            System.out.println("Process Definition ID: " + pd.getId());
            System.out.println("Process Definition Key: " + pd.getKey());
            System.out.println("Process Definition Name: " + pd.getName());
            System.out.println("Process Definition Version: " + pd.getVersion());
            System.out.println("Process Definition Deployment ID: " + pd.getDeploymentId());
        }
    }
    
    public void queryProcessDefinitionById(String processDefinitionId) {
        ProcessDefinition pd = repositoryService.getProcessDefinition(processDefinitionId);
        
        System.out.println("Process Definition ID: " + pd.getId());
        System.out.println("Process Definition Key: " + pd.getKey());
        System.out.println("Process Definition Name: " + pd.getName());
        System.out.println("Process Definition Version: " + pd.getVersion());
        System.out.println("Process Definition Deployment ID: " + pd.getDeploymentId());
    }
}
