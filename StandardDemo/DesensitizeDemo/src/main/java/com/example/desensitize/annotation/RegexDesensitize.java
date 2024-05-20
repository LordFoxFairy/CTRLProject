package com.example.desensitize.annotation;

import com.example.desensitize.handler.DefaultRegexDesensitizationHandler;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;

/**
 * 正则脱敏注解
 */
@Documented
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@DesensitizeBy(handler = DefaultRegexDesensitizationHandler.class)
public @interface RegexDesensitize {
    
    /**
     * 匹配的正则表达式（默认匹配所有）
     */
    String regex() default "^[\\s\\S]*$";
    
    /**
     * 替换规则，会将匹配到的字符串全部替换成 replacer
     */
    String replacer() default "******";
    
}
