package com.example.authority.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.authority.entity.SysUser;
import com.example.authority.service.SysPermissionService;
import com.example.authority.service.SysUserService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户详细信息服务类，实现了 Spring Security 的 UserDetailsService 接口。
 * 用于从数据库或其他持久化存储中加载用户信息。
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysPermissionService sysPermissionService;
    
    
    /**
     * 根据用户名加载用户的详细信息。
     *
     * @param username 要加载的用户的用户名。
     * @return 包含用户详细信息的 UserDetails 对象。
     * <p>
     * String username：用户名
     * String password： 密码
     * boolean enabled： 账号是否可用
     * boolean accountNonExpired：账号是否过期
     * boolean credentialsNonExpired：密码是否过期
     * boolean accountNonLocked：账号是否锁定
     * Collection<? extends GrantedAuthority> authorities)：用户权限列表
     * @throws UsernameNotFoundException 如果找不到对应的用户，则抛出此异常。
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 这里应该根据用户名从数据库或其他地方加载用户的详细信息
        // 如果找到用户，则返回一个 UserDetails 对象，如果找不到则抛出 UsernameNotFoundException 异常
        
        // 这里简单地返回一个硬编码的用户，密码为 "password"，角色为 "USER"
        //        UserDetails user = User.withUsername(username)
        //                .password("{noop}password") // 使用 {noop} 前缀表示密码未加密
        //                .roles("USER")
        //                .build();
        
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("用户不能为空");
        }
        
        //根据用户名查询用户
        SysUser sysUser = sysUserService.selectByName(username);
        if (sysUser == null) {
            throw new RuntimeException("用户不存在");
        }
        
//        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//
//        //获取该用户所拥有的权限
//        List<SysPermission> sysPermissionList = sysPermissionService.selectListByUser(sysUser.getId());
//        // 声明用户授权
//        sysPermissionList.forEach(sysPermission -> {
//            // 表示 code 权限的GrantedAuthority对象，可以将它用于授权用户访问受保护的资源。
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysPermission.getPermissionCode());
//            grantedAuthorityList.add(grantedAuthority);
//        });


        // 使用 User 类的静态方法 withUsername() 创建 UserDetails 对象
        return User.withUsername(sysUser.getAccount())
                // 设置密码，不使用 {noop} 前缀，因为密码已经加密
                .password(sysUser.getPassword())
                // 设置是否账号可用，根据 sysUser 对象的 enabled 字段决定
                .disabled(!sysUser.getEnabled())
                // 设置账号是否过期，根据 sysUser 对象的 notExpired 字段决定
                .accountExpired(!sysUser.getNotExpired())
                // 设置凭证是否过期，根据 sysUser 对象的 credentialsNotExpired 字段决定
                .credentialsExpired(!sysUser.getCredentialsNotExpired())
                // 设置账号是否被锁定，根据 sysUser 对象的 accountNotLocked 字段决定
                .accountLocked(!sysUser.getAccountNotLocked())
                // 设置用户的权限列表
                .authorities(this.sysPermissionService.getGrantedAuthorityList(sysUser))
                // 构建 UserDetails 对象
                .build();

    }
}

