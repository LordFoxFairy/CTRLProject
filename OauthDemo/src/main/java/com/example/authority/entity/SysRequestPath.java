package com.example.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_request_path")
public class SysRequestPath {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private String url;
    
    private String description;
    
}

