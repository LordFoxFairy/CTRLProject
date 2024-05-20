//package com.example.droolsdemo.Demo1.config;
//import org.kie.api.KieServices;
//import org.kie.api.builder.KieBuilder;
//import org.kie.api.builder.KieFileSystem;
//import org.kie.api.builder.ReleaseId;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class DroolsConfig {
//
//    private static final String RULES_PATH = "rules/";
//
//    @Bean
//    public KieServices kieServices() {
//        return KieServices.Factory.get();
//    }
//
//    @Bean
//    public KieFileSystem kieFileSystem() {
//        KieFileSystem kieFileSystem = kieServices().newKieFileSystem();
//        kieFileSystem.write(kieServices().getResources()
//                .newClassPathResource(RULES_PATH + "complex-rule-flow.bpmn"));
//        kieFileSystem.write(kieServices().getResources()
//                .newClassPathResource(RULES_PATH + "rules.drl"));
//        return kieFileSystem;
//    }
//
//    @Bean
//    public KieContainer kieContainer() {
//        KieBuilder kieBuilder = kieServices().newKieBuilder(kieFileSystem());
//        kieBuilder.buildAll();
//        ReleaseId releaseId = kieBuilder.getKieModule().getReleaseId();
//        return kieServices().newKieContainer(releaseId);
//    }
//
//    @Bean
//    public KieSession kieSession() {
//        return kieContainer().newKieSession();
//    }
//}
//
