package com.example.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_permission")
public class SysPermission {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    private String permissionCode;
    
    private String permissionName;
}
