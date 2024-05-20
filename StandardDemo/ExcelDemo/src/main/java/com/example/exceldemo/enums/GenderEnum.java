package com.example.exceldemo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    MALE(1, "男"),
    FEMALE(0, "女");
    
    private final int code;
    private final String name;
    
    public static final Map<Integer, GenderEnum> CODE_ENUM_MAP = new HashMap<>();
    public static final Map<String, GenderEnum> NAME_ENUM_MAP = new HashMap<>();
    
    static {
        for (GenderEnum e : values()) {
            CODE_ENUM_MAP.put(e.getCode(), e);
            NAME_ENUM_MAP.put(e.getName(), e);
        }
    }
    public static GenderEnum getEnumByCode(Integer code) {
        return CODE_ENUM_MAP.getOrDefault(code, null);
    }
    
    public static GenderEnum getEnumByName(String name) {
        return NAME_ENUM_MAP.getOrDefault(name, null);
    }
}
