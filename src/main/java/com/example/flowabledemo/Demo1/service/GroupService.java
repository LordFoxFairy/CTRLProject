package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GroupService {
    
    @Resource
    private IdentityService identityService;
    
    public void createGroup(String groupId, String name) {
        // 创建组对象
        Group group = identityService.newGroup(groupId);
        group.setName(name);
        
        // 保存组
        identityService.saveGroup(group);
    }
}
