package com.example.exception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class ExceptionHandlingDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExceptionHandlingDemoApplication.class, args);
    }

}
