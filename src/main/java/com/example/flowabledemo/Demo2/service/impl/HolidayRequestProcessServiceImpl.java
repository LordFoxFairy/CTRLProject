package com.example.flowabledemo.Demo2.service.impl;

import com.example.flowabledemo.Demo2.dto.HolidayRequestDTO;
import com.example.flowabledemo.Demo2.service.HolidayRequestProcessService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class HolidayRequestProcessServiceImpl implements HolidayRequestProcessService {
    
    @Resource
    private RepositoryService repositoryService;
    
    @Resource
    private RuntimeService runtimeService;
    
    @Resource
    private TaskService taskService;
    
    @Resource
    private HistoryService historyService;
    
    @Override
    public void holidayRequest(HolidayRequestDTO holidayRequestDTO) {
        // 组装参数
        Map<String, Object> variables = new HashMap<>(8);
        variables.put("employee", holidayRequestDTO.getEmployee());
        variables.put("nrOfHolidays", holidayRequestDTO.getNrOfHolidays());
        variables.put("description", holidayRequestDTO.getDescription());
        
        // 启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayRequest", variables);
        // 查询任务，组是“managers”
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        // 打印查询到的任务信息
        System.out.println("You have " + tasks.size() + " tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ") " + tasks.get(i).getName());
        }
        // 获取要执行的任务：查询到的最后一个task
        if (!CollectionUtils.isEmpty(tasks)) {
            Task task = tasks.get(tasks.size() - 1);
            Map<String, Object> processVariables = taskService.getVariables(task.getId());
            System.out.println(processVariables.get("employee") + " wants " +
                    processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");
            
            // 生成随机数，从而决定请求是通过还是拒绝
            boolean approved = ThreadLocalRandom.current().nextInt(1, 10) > 5;
            System.out.println(approved ? "do approved" : "do rejected");
            variables = new HashMap<>();
            variables.put("approved", approved);
            
            // 执行完成
            taskService.complete(task.getId(), variables);
        }
        
        // 历史数据
        List<HistoricActivityInstance> activities =
                historyService.createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstance.getId())
                        .finished()
                        .orderByHistoricActivityInstanceEndTime().asc()
                        .list();
        
        for (HistoricActivityInstance activity : activities) {
            System.out.println(activity.getActivityId() + " took "
                    + activity.getDurationInMillis() + " milliseconds");
        }
    }
    
    
    @PostConstruct
    private void deploy() {
        // 手动部署
        Deployment deployment = repositoryService.createDeployment()
                // 从xml文件读取流程
                .addClasspathResource("holiday-request.bpmn20.xml")
                // 执行部署
                .deploy();
        
        // 根据部署ID获取流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        
        // 输出流程名
        System.out.println("Found process definition : " + processDefinition.getName());
    }
}

