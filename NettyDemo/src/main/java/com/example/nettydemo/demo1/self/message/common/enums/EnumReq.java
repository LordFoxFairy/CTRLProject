package com.example.nettydemo.demo1.self.message.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumReq {
    /*
        请求报文
     */
    REQ((byte)0),
    /*
        返回报文
     */
    RES((byte)1),
    /*
        心跳ping报文
     */
    PING((byte)2),
    /*
        心跳pong报文
     */
    PONG((byte)3);
    
    private final byte code;
    
}
