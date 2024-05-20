package com.example.crontaskdemo.CronTaskDemoOne.controller;

import com.example.crontaskdemo.CronTaskDemoOne.manager.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    private final TaskManager taskManager;
    
    @Autowired
    public TaskController(TaskManager taskManager) {
        this.taskManager = taskManager;
    }
    
    @GetMapping("/{taskName}")
    public ResponseEntity<String> createTask(@PathVariable String taskName,
                                             @RequestParam(required = false) String cronExpression) {
        Runnable task = () -> System.out.println("Hello " + taskName);
        try {
            taskManager.createTask(taskName, task, cronExpression);
            return ResponseEntity.ok("Task created successfully: " + taskName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{taskName}/start")
    public ResponseEntity<String> startTask(@PathVariable String taskName,
                                            @RequestParam String cronExpression) {
        try {
            taskManager.startTask(taskName, cronExpression);
            return ResponseEntity.ok("Task started successfully: " + taskName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{taskName}/stop")
    public ResponseEntity<String> stopTask(@PathVariable String taskName) {
        try {
            taskManager.stopTask(taskName);
            return ResponseEntity.ok("Task stopped successfully: " + taskName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

