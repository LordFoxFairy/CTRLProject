package com.example.crontaskdemo.CronTaskDemoOne.manager;

import com.example.crontaskdemo.CronTaskDemoOne.entity.TaskStatus;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;

@Component
public class TaskManager {
    
    private static TaskManager instance;
    
    private final ThreadPoolTaskScheduler taskScheduler;
    private final Map<String, ScheduledFuture<?>> taskFutures;
    private final Map<String, Runnable> taskRunnables;
    private final Semaphore taskSemaphore;
    
    private final Object lock = new Object(); // 用于同步的锁对象
    
    private TaskManager(ThreadPoolTaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
        this.taskFutures = new ConcurrentHashMap<>();
        this.taskRunnables = new ConcurrentHashMap<>();
        this.taskSemaphore = new Semaphore(5); // 默认限制同时执行的任务数量为 5
    }
    
    public static synchronized TaskManager getInstance(ThreadPoolTaskScheduler taskScheduler) {
        if (instance == null) {
            instance = new TaskManager(taskScheduler);
        }
        return instance;
    }
    
    public void createTask(String taskName, Runnable task, String cronExpression) {
        synchronized (lock) {
            if (!taskFutures.containsKey(taskName)) {
                ScheduledFuture<?> future = taskScheduler.schedule(task, new CronTrigger(cronExpression));
                taskFutures.put(taskName, future);
                taskRunnables.put(taskName, task);
            } else {
                throw new IllegalArgumentException("Task already exists: " + taskName);
            }
        }
    }
    
    public void startTask(String taskName, String cronExpression) {
        Runnable task = taskRunnables.get(taskName);
        if (task != null) {
            ScheduledFuture<?> future = taskFutures.get(taskName);
            if (future != null) {
                future.cancel(false);
            }
            taskFutures.put(taskName, taskScheduler.schedule(task, new CronTrigger(cronExpression)));
        } else {
            throw new IllegalArgumentException("Task not found: " + taskName);
        }
    }
    
    public void stopTask(String taskName) {
        ScheduledFuture<?> future = taskFutures.get(taskName);
        if (future != null) {
            future.cancel(false);
        } else {
            throw new IllegalArgumentException("Task not found: " + taskName);
        }
    }
    
    public int getActiveTaskCount() {
        return taskSemaphore.availablePermits();
    }
    
    public TaskStatus getTaskStatus(String taskName) {
        ScheduledFuture<?> future = taskFutures.get(taskName);
        if (future != null) {
            if (future.isCancelled()) {
                return new TaskStatus(taskName, "Cancelled");
            } else if (future.isDone()) {
                return new TaskStatus(taskName, "Done");
            } else {
                return new TaskStatus(taskName, "Running");
            }
        } else {
            throw new IllegalArgumentException("Task not found: " + taskName);
        }
    }
    
    public List<TaskStatus> getAllTaskStatus() {
        List<TaskStatus> statuses = new ArrayList<>();
        for (Map.Entry<String, ScheduledFuture<?>> entry : taskFutures.entrySet()) {
            String taskName = entry.getKey();
            ScheduledFuture<?> future = entry.getValue();
            String status;
            if (future.isCancelled()) {
                status = "Cancelled";
            } else if (future.isDone()) {
                status = "Done";
            } else {
                status = "Running";
            }
            statuses.add(new TaskStatus(taskName, status));
        }
        return statuses;
    }
}
