package com.example.nettydemo.demo1.self.message.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MessageRecord implements Serializable {
    /*
        消息内容长度
     */
    private int length;
    /*
        消息头
     */
    private MessageHeader messageHeader;
    /*
        消息体
     */
    private Object body;
}
