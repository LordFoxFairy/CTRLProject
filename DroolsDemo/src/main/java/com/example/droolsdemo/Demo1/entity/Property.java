package com.example.droolsdemo.Demo1.entity;

import lombok.Data;

@Data
public class Property {
    private int threshold;
    private String operator;
    private boolean range;
    private int min;
    private int max;
    
    // 构造函数，用于设置阈值和操作符
    public Property(int threshold, String operator) {
        this.threshold = threshold;
        this.operator = operator;
    }
    
    // 另一个构造函数，用于设置阈值、操作符以及范围
    public Property(int threshold, String operator, boolean range, int min, int max) {
        this.threshold = threshold;
        this.operator = operator;
        this.range = range;
        this.min = min;
        this.max = max;
    }
}