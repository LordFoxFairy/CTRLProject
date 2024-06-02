package com.example.flowabledemo;

import com.example.flowabledemo.Demo1.service.*;
import liquibase.pro.packaged.R;
import org.flowable.engine.FormService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.form.StartFormData;
import org.flowable.engine.form.TaskFormData;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class FormServiceTest {
    
    @Resource
    private TaskService taskService;
    
    @Resource
    private FormServiceExample formServiceExample;
    
    @Resource
    TaskFormDataService taskFormDataService;
    
    @Resource
    private FlowableRuntimeService flowableRuntimeService;
    
    @Resource
    private FlowableTaskService flowableTaskService;
    
    @Test
    public void testFormService() {
        
        // 部署流程定义并获取ID
        ProcessInstance processInstance = flowableRuntimeService.startProcessInstance("myProcess3");
        
        // 调用服务创建任务
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        
        String taskId = task.getId();
        System.out.println("task =" + task);
        
        // 获取任务表单数据
        TaskFormData taskFormData = taskFormDataService.getTaskFormData(taskId);
        System.out.println("taskFormData = " + taskFormData);
        
        formServiceExample.submitTaskForm(taskId);
        
        // 打印提交后任务状态
        Task updatedTask = taskService.createTaskQuery().taskId(taskId).singleResult();
        System.out.println("Task after form submission: " + updatedTask);

    }
}
