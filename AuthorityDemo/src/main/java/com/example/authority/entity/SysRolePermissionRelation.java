package com.example.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_role_permission_relation")
public class SysRolePermissionRelation {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private Integer roleId;
    
    private Integer permissionId;
    
}
