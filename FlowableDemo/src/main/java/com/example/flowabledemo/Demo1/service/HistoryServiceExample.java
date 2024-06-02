package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.HistoryService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.history.HistoricProcessInstanceQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HistoryServiceExample {

    @Resource
    private HistoryService historyService;

    public void queryHistoricProcessInstances() {
        // 创建查询已完成的流程实例的查询对象
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();

        // 设置查询条件（例如流程定义的Key）
        query.processDefinitionKey("myProcess");

        // 执行查询
        List<HistoricProcessInstance> historicProcessInstances = query.list();

        // 处理查询结果
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstances) {
            System.out.println("Historic Process Instance ID: " + historicProcessInstance.getId());
            System.out.println("Start Time: " + historicProcessInstance.getStartTime());
            System.out.println("End Time: " + historicProcessInstance.getEndTime());
            // 其他属性和方法
        }
    }
}
