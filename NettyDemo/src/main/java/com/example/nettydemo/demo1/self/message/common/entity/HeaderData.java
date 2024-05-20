package com.example.nettydemo.demo1.self.message.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class HeaderData implements Serializable {
    /*
        版本号
     */
    private int version;
    /*
        编程语言
     */
    private byte languageCode;
    /*
        序列化方式
     */
    private byte serializableType;
    /*
        请求类型
     */
    private byte reqType;
}
