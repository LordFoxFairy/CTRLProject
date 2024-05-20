package com.example.desensitize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class IdempotentDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(IdempotentDemoApplication.class, args);
    }

}
