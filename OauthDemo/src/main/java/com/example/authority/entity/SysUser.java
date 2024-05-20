package com.example.authority.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sys_user")
public class SysUser {
    
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    private String account;
    
    private String userName;
    
    private String password;
    
    private Date lastLoginTime;
    
    private Boolean enabled;
    
    private Boolean notExpired;
    
    private Boolean accountNotLocked;
    
    private Boolean credentialsNotExpired;
    
    private Date createTime;
    
    private Date updateTime;
    
    private Integer createUser;
    
    private Integer updateUser;
    
}
