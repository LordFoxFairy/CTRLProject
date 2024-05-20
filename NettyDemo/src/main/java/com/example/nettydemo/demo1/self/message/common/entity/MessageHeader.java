package com.example.nettydemo.demo1.self.message.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageHeader implements Serializable {
    /*
        消息头长度
     */
    private int headerLength;
    /*
        消息内容
     */
    private HeaderData headerData;
}
