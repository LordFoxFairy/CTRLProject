//package com.example.droolsdemo.DemoX.config;
//
//import com.example.droolsdemo.DemoX.entity.DrlRule;
//import com.example.droolsdemo.DemoX.mapper.DrlRuleMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.kie.api.KieBase;
//import org.kie.api.KieServices;
//import org.kie.api.builder.KieBuilder;
//import org.kie.api.builder.KieFileSystem;
//import org.kie.api.builder.KieRepository;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import javax.annotation.Resource;
//import java.io.StringReader;
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Configuration
//@Slf4j
//public class KieSessionConfig {
//
//    @Resource
//    private DrlRuleMapper drlRuleMapper;
//
//    private final Map<Object, Object> loadedRules = new HashMap<>();
//
//    @Bean
//    public KieContainer kieContainer() {
//        KieServices kieServices = KieServices.Factory.get();
//        KieRepository kieRepository = kieServices.getRepository();
//        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
//
//        KieBuilder kieBuilder = kieServices.newKieBuilder(loadDrlFilesFromDatabase(kieServices));
//        kieBuilder.buildAll();
//
//        return kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
//    }
//
//    private KieFileSystem loadDrlFilesFromDatabase(KieServices kieServices) {
//        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
//        // 从数据库加载DRL规则内容
//        List<DrlRule> drlRules = drlRuleMapper.selectList(null);
//        for (DrlRule rule : drlRules) {
//            String drlContent = rule.getDrlContent();
//            kieFileSystem.write("src/main/resources/" + rule.getRuleName() + ".drl",
//                    kieServices.getResources().newReaderResource(new StringReader(drlContent)));
//            loadedRules.put(rule.getId(), rule.getRuleName()); // 将加载的规则添加到全局Map中
//        }
//
//        return kieFileSystem;
//    }
//
//    @Bean
//    public KieBase kieBase() {
//        return kieContainer().getKieBase();
//    }
//
//    @Bean
//    public KieSession kieSession() {
//        KieSession kieSession = kieContainer().newKieSession();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        kieSession.setGlobal("fmt", simpleDateFormat);
//        return kieSession;
//    }
//
//    // 提供一个方法来获取已加载的规则信息
//    public Map<Object, Object> getLoadedRules() {
//        return loadedRules;
//    }
//
//    // 定时检查数据库变更，并同步规则及Map
//    @Scheduled(cron="0/5 * *  * * ? ")   //每5秒执行一次
//    public void synchronizeRulesWithDatabase() {
//
//        // 检查数据库更新
//        List<DrlRule> drlRules = drlRuleMapper.selectList(null);
//        Map<Object, Object> newRulesMap = new HashMap<>();
//        for (DrlRule rule : drlRules) {
//            newRulesMap.put(rule.getId(), rule.getRuleName()); // 将加载的规则添加到临时Map中
//        }
//
//        // 检查是否有新增的规则
//        boolean hasNewRules = false;
//        for (Map.Entry<Object, Object> entry : newRulesMap.entrySet()) {
//            if (!loadedRules.containsKey(entry.getKey())) {
//                hasNewRules = true;
//                break;
//            }
//        }
//
//        if (hasNewRules) {
//
//            log.info("{}", this.getLoadedRules());
//
//            // 更新规则Map
//            loadedRules.clear();
//            loadedRules.putAll(newRulesMap);
//
//            // 重新加载规则
//            KieServices kieServices = KieServices.Factory.get();
//            KieFileSystem kieFileSystem = loadDrlFilesFromDatabase(kieServices);
//            KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
//            kieBuilder.buildAll();
//            KieRepository kieRepository = kieServices.getRepository();
//            kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
//        }
//    }
//}
