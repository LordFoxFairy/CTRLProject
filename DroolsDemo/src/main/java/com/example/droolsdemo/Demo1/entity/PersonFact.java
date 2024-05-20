package com.example.droolsdemo.Demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonFact {
    private String name;
    private Date birthday;
    
    private Integer age;
}
