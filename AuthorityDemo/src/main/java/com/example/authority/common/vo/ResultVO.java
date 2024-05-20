package com.example.authority.common.vo;

import com.example.authority.common.entity.ResponseEntity;
import com.example.authority.enums.ResultCode;

public class ResultVO<T> {
    public static <T> ResponseEntity<T> success() {
        return new ResponseEntity<>(true);
    }
    
    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(true, data);
    }
    
    public static <T> ResponseEntity<T> fail() {
        return new ResponseEntity<>(false);
    }
    
    public static <T> ResponseEntity<T> fail(ResultCode resultEnum) {
        return new ResponseEntity<>(false, resultEnum);
    }
}
