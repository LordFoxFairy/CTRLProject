package com.example.authority.filter;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 自定义访问决策管理器
 * 用于决定当前用户是否具有访问请求所需权限
 */
@Component
public class CustomizeAccessDecisionManager implements AccessDecisionManager {
    
    /**
     * 判断当前用户是否具有访问请求所需权限
     *
     * @param authentication   当前用户的认证信息
     * @param object           请求对象
     * @param collection       当前请求所需的权限集合
     * @throws AccessDeniedException               当权限不足时抛出
     * @throws InsufficientAuthenticationException 当用户认证信息不足时抛出
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // 遍历当前请求所需的权限集合
        for (ConfigAttribute configAttribute : collection) {
            // 当前请求需要的权限
            String needRole = configAttribute.getAttribute();
            
            // 当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            
            // 遍历当前用户所具有的权限集合
            for (GrantedAuthority authority : authorities) {
                // 如果用户具有当前请求所需的权限，则返回
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }
        // 如果用户没有当前请求所需的权限，则抛出权限不足异常
        throw new AccessDeniedException("权限不足!");
    }
    
    // 是否支持指定的配置属性，这里返回true表示支持任意配置属性
    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }
    
    // 是否支持指定的类，这里返回true表示支持任意类型的类
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}