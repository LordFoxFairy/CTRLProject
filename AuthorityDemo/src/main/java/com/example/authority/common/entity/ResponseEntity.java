package com.example.authority.common.entity;

import com.example.authority.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> implements Serializable {
    private Boolean status;
    private Integer errorCode;
    private String errorMsg;
    private T data;
    
    public ResponseEntity(boolean status) {
        this.status = status;
        this.errorCode = status ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.errorMsg = status ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
    }
    
    public ResponseEntity(boolean status, T data) {
        this.status = status;
        this.data = data;
        this.errorCode = status ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode();
        this.errorMsg = status ? ResultCode.SUCCESS.getMessage() : ResultCode.COMMON_FAIL.getMessage();
    }
    
    public ResponseEntity(boolean status, ResultCode resultEnum) {
        this.status = status;
        this.errorCode = status ? ResultCode.SUCCESS.getCode() :
                (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.errorMsg = status ? ResultCode.SUCCESS.getMessage() :
                (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
    }
    
    public ResponseEntity(boolean status, ResultCode resultEnum, T data) {
        this.status = status;
        this.errorCode = status ? ResultCode.SUCCESS.getCode() : (resultEnum == null ? ResultCode.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.errorMsg = status ? ResultCode.SUCCESS.getMessage() : (resultEnum == null ? ResultCode.COMMON_FAIL.getMessage() : resultEnum.getMessage());
        this.data = data;
    }
}
