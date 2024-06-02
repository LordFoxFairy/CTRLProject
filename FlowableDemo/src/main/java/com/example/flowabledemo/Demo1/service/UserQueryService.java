package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.IdentityService;
import org.flowable.idm.api.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserQueryService {
    
    @Resource
    private IdentityService identityService;
    
    public User getUserById(String userId) {
        // 创建查询对象
        return identityService.createUserQuery()
                .userId(userId)
                .singleResult();
    }
}
