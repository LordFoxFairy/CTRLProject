package com.example.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user_role_relation")
public class SysUserRoleRelation {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private Integer userId;
    
    private Integer roleId;
    
}
