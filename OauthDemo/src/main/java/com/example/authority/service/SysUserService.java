package com.example.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.authority.entity.SysUser;

import java.util.List;

public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户实体
     */
    SysUser selectByName(String userName);
    
    List<SysUser> selectList();
}
