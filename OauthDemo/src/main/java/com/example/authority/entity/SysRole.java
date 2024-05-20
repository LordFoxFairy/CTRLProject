package com.example.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_role")
public class SysRole {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private String roleName;
    
    private String roleDescription;
    
    private String roleCode;
    
}
