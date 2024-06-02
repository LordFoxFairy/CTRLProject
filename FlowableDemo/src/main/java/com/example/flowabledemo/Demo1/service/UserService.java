package com.example.flowabledemo.Demo1.service;

import org.flowable.engine.IdentityService;
import org.flowable.idm.api.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    
    @Resource
    private IdentityService identityService;
    
    public void createUser(String userId, String firstName, String lastName, String email, String password) {
        // 创建用户对象
        User user = identityService.newUser(userId);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        
        // 保存用户
        identityService.saveUser(user);
    }
    
    public void deleteUser(String userId) {
        // 删除用户
        identityService.deleteUser(userId);
    }
}
