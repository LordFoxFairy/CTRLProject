package com.example.nettydemo.demo1.self.message.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumSerializer {
    
    /*
        JAVA序列化
     */
    JAVA((byte)0),
    /*
        JSON序列化
     */
    JSON((byte)1),
    /*
        Protobuf序列化
     */
    PROTOBUF((byte)2);
    
    private final byte code;
}
