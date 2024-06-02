package com.example.droolsdemo.Demo1.config;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.conf.KieBaseMutabilityOption;
import org.kie.api.conf.KieBaseOption;
import org.kie.api.conf.KieBaseOptionsConfiguration;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

@Configuration
@Slf4j
public class DroolsConfig2 {
    
    private static final String RULES_PATH = "rules/";
    
    private KieServices getKieServices() {
        return KieServices.Factory.get();
    }
    
    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.*");
    }
    
    
    @Bean
    public KieContainer kieContainer() throws IOException {
        
        KieHelper helper = new KieHelper();
        
        KieBaseConfiguration config = helper.ks.newKieBaseConfiguration();
        config.setOption(EventProcessingOption.STREAM);
        
        for (Resource file : getRuleFiles()) {
            String fileName = file.getFilename();
            if (fileName != null) {
                log.info("Processing file: {}", file.getFile());
                if (fileName.endsWith(".drl")) {
                    helper.addResource(ResourceFactory.newClassPathResource(RULES_PATH + fileName, "UTF-8"), ResourceType.DRL);
                } else if (fileName.endsWith(".bpmn2") || fileName.endsWith(".bpmn")) {
                    helper.addResource(ResourceFactory.newClassPathResource(RULES_PATH + fileName, "UTF-8"), ResourceType.BPMN2);
                } else {
                    log.warn("Unknown file type: {}", fileName);
                }
            }
        }
        
        return helper.getKieContainer();
    }
    
    @Bean
    public KieBase kieBase() throws IOException {
        KieHelper helper = new KieHelper();
        KieBaseConfiguration config = helper.ks.newKieBaseConfiguration();
        config.setOption(EventProcessingOption.STREAM);
        return kieContainer().newKieBase(config);
    }
    
}