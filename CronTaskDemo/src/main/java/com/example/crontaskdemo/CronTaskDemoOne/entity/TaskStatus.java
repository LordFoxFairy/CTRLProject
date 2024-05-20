package com.example.crontaskdemo.CronTaskDemoOne.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskStatus {
    private String taskName;
    private String status;
}

