package com.example.desensitize.entity;

import com.example.desensitize.annotation.SliderDesensitize;
import lombok.Data;

@Data
public class UserEntity {
    @SliderDesensitize(prefixKeep = 1, suffixKeep = 2)
    private String phone;
}
