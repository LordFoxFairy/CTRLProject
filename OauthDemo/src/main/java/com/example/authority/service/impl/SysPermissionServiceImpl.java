package com.example.authority.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.authority.entity.SysPermission;
import com.example.authority.entity.SysUser;
import com.example.authority.mapper.SysPermissionMapper;
import com.example.authority.service.SysPermissionService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
    @Override
    public List<SysPermission> selectListByUser(Integer userId) {
        return this.baseMapper.selectListByUser(userId);
    }
    
    @Override
    public List<SysPermission> selectListByPath(String requestUrl) {
        return null;
    }
    
    @Override
    public List<GrantedAuthority> getGrantedAuthorityList(SysUser sysUser) {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        //获取该用户所拥有的权限
        List<SysPermission> sysPermissionList = this.selectListByUser(sysUser.getId());
        // 声明用户授权
        sysPermissionList.forEach(sysPermission -> {
            // 表示 code 权限的GrantedAuthority对象，可以将它用于授权用户访问受保护的资源。
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
            grantedAuthorityList.add(grantedAuthority);
        });
        return grantedAuthorityList;
    }
}
