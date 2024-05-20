package com.example.authority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.authority.entity.SysPermission;
import com.example.authority.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;


public interface SysPermissionService extends IService<SysPermission>{
    /**
     * 查询用户的权限列表
     *
     * @param userId
     * @return SysPermission
     */
    List<SysPermission> selectListByUser(Integer userId);
    
    List<SysPermission> selectListByPath(String requestUrl);
    
    List<GrantedAuthority> getGrantedAuthorityList(SysUser sysUser);
}
