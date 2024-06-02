package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class FlowableDeploymentService {
    
    @Resource
    private RepositoryService repositoryService;
    
    @Transactional
    public void deployProcessDefinition() {
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/my-process.bpmn20.xml")
                .name("My Process Deployment")
                .deploy();
        
        System.out.println("Deployment ID: " + deployment.getId());
    }
}
