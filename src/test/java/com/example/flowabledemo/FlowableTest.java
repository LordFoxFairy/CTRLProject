package com.example.flowabledemo;

import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.*;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class FlowableTest {
    
    @Resource
    private RepositoryService repositoryService;
    @Resource
    RuntimeService runtimeService;
    @Resource
    TaskService taskService;
    
    
    @Test
    public void test(){
        // 部署流程定义
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/请假流程-排他网关.bpmn20.xml")
                .deploy();
        
        // 设置流程变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("num", 2); // 假设请假天数为2
        
        // 启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("LeaveProcessExclusiveGateway", variables);
        
        // 获取并完成第一个任务：提交请假申请
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务：" + task.getName());
        taskService.complete(task.getId());
        
        // 根据条件获取下一个任务：上级领导审批 或 总经理审批
        task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务：" + task.getName());
        taskService.complete(task.getId());
        
        // 获取财务审核任务
        task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务：" + task.getName());
        taskService.complete(task.getId());
        
        // 验证流程结束
        ProcessInstance result = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("流程结束：" + (result == null));
    }
    
    
    @Test
    public void test1(){
        
        // 部署流程定义
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/请假流程-并行网关.bpmn20.xml")
                .deploy();
        
        Map<String, Object> variables = new HashMap<>();
        variables.put("employee", "John Doe");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("LeaveRequestProcessParallelGateway", variables);
        
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        for (Task task : tasks) {
            System.out.println("Task available: " + task.getName());
        }
        
        // Completing tasks for demonstration purposes
        for (Task task : tasks) {
            taskService.complete(task.getId());
        }
        
        tasks = taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
        for (Task task : tasks) {
            System.out.println("Task available after completion: " + task.getName());
        }
    }
    
    @Test
    public void test3(){
        // 部署流程定义
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/请假流程-包容网关.bpmn20.xml")
                .deploy();
        
        // 设置流程变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("num", 3); // 假设请假天数为3
        
        // 启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("LeaveProcessExclusiveGateway", variables);
        
        // 获取并完成第一个任务：提交请假申请
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务：" + task.getName());
        taskService.complete(task.getId());
        
        // 根据条件获取下一个任务：上级领导审批 或 总经理审批
        task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务：" + task.getName());
        taskService.complete(task.getId());
        
        // 获取财务审核任务
        task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务：" + task.getName());
        taskService.complete(task.getId());
        
        // 验证流程结束
        ProcessInstance result = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("流程结束：" + (result == null));
    }
    
    
    @Test
    public void testEventGatewayProcess() {
        // 部署流程定义
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/请假流程-事件网关.bpmn20.xml")
                .deploy();
        
        // 启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("LeaveProcessEventGateway");
        
        // 获取并完成第一个任务：提交请假申请
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("当前任务：" + task.getName());
        taskService.complete(task.getId());
        
        // 获取流程定义ID
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).singleResult();
        
        // 获取BPMN模型
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
        Process process = bpmnModel.getMainProcess();
        
        // 遍历所有FlowElement，找到EventGateway并获取其后连接的IntermediateCatchEvent
        for (FlowElement flowElement : process.getFlowElements()) {
            if (flowElement instanceof EventGateway) {
                EventGateway eventGateway = (EventGateway) flowElement;
                List<IntermediateCatchEvent> catchEvents = process.findFlowElementsOfType(IntermediateCatchEvent.class, false);
                for (IntermediateCatchEvent catchEvent : catchEvents) {
                    if (eventGateway.getOutgoingFlows().stream().anyMatch(flow -> flow.getTargetRef().equals(catchEvent.getId()))) {
                        System.out.println("找到中间捕获事件: " + catchEvent.getId());
                        // 手动触发中间捕获事件
                        if (catchEvent.getEventDefinitions().get(0) instanceof TimerEventDefinition) {
                            // 模拟定时器事件触发 (这里我们可以等待一段时间或者直接完成)
                            // Note: 定时器事件在测试中通常是模拟的
                        } else if (catchEvent.getEventDefinitions().get(0) instanceof SignalEventDefinition) {
                            // 触发信号事件
                            runtimeService.signalEventReceived("approvalSignal");
                        }
                    }
                }
            }
        }
        
        // 循环完成所有剩余任务，直到流程结束
        while (true) {
            task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
            if (task == null) {
                break;
            }
            System.out.println("当前任务：" + task.getName());
            taskService.complete(task.getId());
        }
        
        // 验证流程结束
        ProcessInstance result = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
        System.out.println("流程结束：" + (result == null));
    }
}
