package com.example.droolsdemo.Demo1.entity;

public class RuleEngineFact {
    private String name;
    private Number value;
    
    public RuleEngineFact(String name, Number value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Number getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "RuleEngineFact{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}

