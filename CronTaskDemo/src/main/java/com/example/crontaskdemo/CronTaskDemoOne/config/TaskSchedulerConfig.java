package com.example.crontaskdemo.CronTaskDemoOne.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class TaskSchedulerConfig {
    
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        // 设置线程池大小
        scheduler.setPoolSize(10);
        // 设置线程名前缀
        scheduler.setThreadNamePrefix("taskScheduler-");
        return scheduler;
    }
}