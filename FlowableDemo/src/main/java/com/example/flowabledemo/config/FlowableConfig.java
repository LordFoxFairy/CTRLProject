package com.example.flowabledemo.config;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {
    
    @Resource
    private DataSource dataSource;
    
    @Override
    public void configure(SpringProcessEngineConfiguration config) {
        config.setDataSource(dataSource);
        config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    }
    
    @Bean
    public ProcessEngine processEngine(SpringProcessEngineConfiguration configuration) {
        return configuration.buildProcessEngine();
    }
    
}


