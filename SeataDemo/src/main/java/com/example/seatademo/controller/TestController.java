package com.example.seatademo.controller;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @SneakyThrows
    @GetMapping("/test1")
    @GlobalTransactional
    public void test() {
        System.out.println("RootContext.getXID():" + RootContext.getXID());
        Object a = null;
        GlobalTransactionContext.reload(RootContext.getXID()).rollback();
        System.out.println("RootContext.getXID():" + RootContext.getXID());
    }
    
}

