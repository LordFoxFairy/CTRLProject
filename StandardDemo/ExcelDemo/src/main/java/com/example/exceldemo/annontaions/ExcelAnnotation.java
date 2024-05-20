package com.example.exceldemo.annontaions;

import java.lang.annotation.*;

/**
 * 指定枚举类注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelAnnotation {
    /**
     * 控件类型
     * @return
     */
    Class<? extends Enum> type();
}

