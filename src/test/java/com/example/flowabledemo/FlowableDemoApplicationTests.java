package com.example.flowabledemo;

import com.example.flowabledemo.Demo1.service.*;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.idm.api.User;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@Slf4j
class FlowableDemoApplicationTests {
    
    @Resource
    private FlowableDeploymentService flowableDeploymentService;
    
    @Resource
    private FlowableQueryService flowableQueryService;
    
    @Resource
    private FlowableProcessDefinitionService flowableProcessDefinitionService;
    
    @Resource
    private FlowableRuntimeService flowableRuntimeService;
    
    @Resource
    private FlowableProcessInstanceQueryService flowableProcessInstanceQueryService;
    
    @Resource
    private FlowableVariableService flowableVariableService;
    
    
    @Test
    void contextLoads() {
//        flowableDeploymentService.deployProcessDefinition();
//        flowableQueryService.queryProcessDefinitions();

//        flowableProcessDefinitionService.queryProcessDefinitions();
//        // 部署流程定义并获取ID
//        String processDefinitionId = flowableQueryService.queryProcessDefinitions();
//
//        // 使用获取到的流程定义ID查询详细信息
//        if (processDefinitionId != null) {
//            flowableProcessDefinitionService.queryProcessDefinitionById(processDefinitionId);
//        }
        
        
        // 部署流程定义并获取ID
        ProcessDefinition processDefinition = flowableQueryService.queryProcessDefinitions();
        
        // 使用获取到的流程定义ID查询详细信息
        if (processDefinition != null) {
            ProcessInstance processInstance = flowableRuntimeService.startProcessInstanceByKey(processDefinition.getKey(), "AAAA");
            flowableProcessInstanceQueryService.getProcessInstanceByBusinessKey(processInstance.getBusinessKey());
            
            
//            String processInstanceId = processInstance.getId();
//            String variableName = "testVariable";
//            String variableValue = "testValue";
//            flowableVariableService.setProcessVariable(processInstanceId, variableName, variableValue);
//
//            Map<String, Object> processVariables = flowableVariableService.getProcessVariables(processInstanceId);
//
//            System.out.println("processVariables : " + processVariables);
        }
        
        
    }
    
    
    @Resource
    private FlowableTaskService taskService;
    
    @Resource
    private FlowableQueryService queryService;
    
    @Resource
    private FlowableCompletionService completionService;
    
    @Test
    void test() {
        // Step 1: 创建任务
        String name = "Task 1";
        String description = "This is task 1";
        String assignee = "user1";
        Task task = taskService.createTask(name, description, assignee);
        
        // Step 2: 查询任务
        List<Task> tasks = queryService.getAllTasks();
        System.out.println("tasks:" + tasks);
        
        // Step 3: 完成任务
        completionService.completeTask(task.getId());
        
        // Step 4: 查询已完成的任务
        tasks = queryService.getAllTasks();
        System.out.println("tasks:" + tasks);
        
    }
    
    
    @Test
    public void testJavaDelegate() {
        // 部署流程定义
        flowableDeploymentService.deployProcessDefinition();
        
        // 启动流程实例
        ProcessInstance processInstance = flowableRuntimeService.startProcessInstance("myProcess", Collections.singletonMap("myVariable", "initialValue"));
        
        // 打印流程实例信息
        flowableRuntimeService.printProcessInstanceInfo(processInstance);
    }
    
    @Resource
    private UserService userService;
    
    @Resource
    private GroupService groupService;
    
    @Resource
    private MembershipService membershipService;
    
    @Resource
    private UserQueryService userQueryService;
    
    @Test
    public void testUserAndGroupManagement() {
        // 创建用户
        String userId = "user2";
        userService.createUser(userId, "John", "Doe", "john.doe@example.com", "password");
        
        // 查询用户
        User user = userQueryService.getUserById(userId);
        log.info("user:{}", user.toString());
        
        // 创建组
        String groupId = "group2";
        groupService.createGroup(groupId, "Managers");
        
        // 将用户添加到组
        membershipService.addUserToGroup(userId, groupId);
        
        // 删除用户
        userService.deleteUser(userId);
        
    }
    
    
    @Resource
    private HistoryServiceExample historyServiceExample;
    
    @Test
    public void test2(){
        historyServiceExample.queryHistoricProcessInstances();
    }
    
    
}
