package com.example.desensitize.controller;

import com.example.desensitize.entity.UserEntity;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@Api(tags = "测试脱敏")
public class TestController {
    @GetMapping("/get")
    public UserEntity get(){
        UserEntity user = new UserEntity();
        
        user.setPhone("18892653546");
        
        return user;
    }
}
