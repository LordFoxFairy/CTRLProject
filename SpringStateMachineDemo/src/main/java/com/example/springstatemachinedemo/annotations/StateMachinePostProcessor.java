package com.example.springstatemachinedemo.annotations;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.statemachine.annotation.OnStateEntry;
import org.springframework.statemachine.annotation.OnStateExit;

import java.lang.reflect.Method;

@Configuration
public class StateMachinePostProcessor implements BeanPostProcessor {
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (AnnotatedElementUtils.isAnnotated(method, StatesOnStateEntry.class)) {
                StatesOnStateEntry annotation = AnnotationUtils.findAnnotation(method, StatesOnStateEntry.class);
                if (annotation != null) {
                    OnStateEntry onStateEntry = AnnotationUtils.synthesizeAnnotation(OnStateEntry.class);
                    method.setAccessible(true);
                    AnnotationUtils.synthesizeAnnotation(onStateEntry, method);
                }
            }
            if (AnnotatedElementUtils.isAnnotated(method, StatesOnStateExit.class)) {
                StatesOnStateExit annotation = AnnotationUtils.findAnnotation(method, StatesOnStateExit.class);
                if (annotation != null) {
                    OnStateExit onStateExit = AnnotationUtils.synthesizeAnnotation(OnStateExit.class);
                    method.setAccessible(true);
                    AnnotationUtils.synthesizeAnnotation(onStateExit, method);
                }
            }
        }
        return bean;
    }
}
