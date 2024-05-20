package com.example.nettydemo.demo1.self.message.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumCode {
    /*
        JAVA
     */
    JAVA((byte)0),
    /*
        PYTHON
     */
    PYTHON((byte)1),
    /*
        GO
     */
    GO((byte)2);
    
    private final byte code;
}
