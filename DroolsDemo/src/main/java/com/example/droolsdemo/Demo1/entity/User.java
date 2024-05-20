package com.example.droolsdemo.Demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private Integer age;
    private Integer level;
    
    
    public List<User> getUserList(){
        return new ArrayList<>();
    }
}
