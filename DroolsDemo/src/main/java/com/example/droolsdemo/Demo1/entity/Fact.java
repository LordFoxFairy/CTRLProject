package com.example.droolsdemo.Demo1.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fact {
    private String name;
    private Integer age;
}
