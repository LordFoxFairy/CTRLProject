package com.example.desensitize.controller;

import com.example.desensitize.annotation.Idempotent;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@Api(tags = "测试幂等")
public class TestController {
    @GetMapping("/get")
    @Idempotent(timeout = 10, message = "重复请求，请稍后重试")
    public ResponseEntity<String> get(@RequestParam("id") Long id){
        try {
            // 执行可能抛出异常的代码
            // 这里放置您的业务逻辑代码
            return ResponseEntity.ok("操作成功");
        } catch (Exception e) {
            // 处理异常情况并返回异常消息
            return ResponseEntity.ok().body(e.getMessage());
        }
    }
}
