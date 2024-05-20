package com.example.nettydemo.demo1.self.message.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    
    private String name;
    
    private int age;
}
