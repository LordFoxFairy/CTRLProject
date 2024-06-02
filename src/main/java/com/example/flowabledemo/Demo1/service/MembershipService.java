package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.IdentityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MembershipService {
    
    @Resource
    private IdentityService identityService;
    
    public void addUserToGroup(String userId, String groupId) {
        // 将用户添加到组
        identityService.createMembership(userId, groupId);
    }
}
