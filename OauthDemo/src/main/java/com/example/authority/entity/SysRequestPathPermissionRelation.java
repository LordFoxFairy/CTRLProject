package com.example.authority.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_request_path_permission_relation")
public class SysRequestPathPermissionRelation {
    
    private Integer id;
    
    private Integer urlId;
    
    private Integer permissionId;
    
}
