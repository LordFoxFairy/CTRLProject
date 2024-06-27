package com.example.springstatemachinedemo.annotations;

import com.example.springstatemachinedemo.enums.States;
import org.springframework.statemachine.annotation.OnStateEntry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnStateEntry
public @interface StatesOnStateEntry {
    States[] source() default {};
    
    States[] target() default {};
}
