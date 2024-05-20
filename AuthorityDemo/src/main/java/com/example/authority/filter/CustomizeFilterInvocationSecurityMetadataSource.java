package com.example.authority.filter;

import com.example.authority.entity.SysPermission;
import com.example.authority.service.SysPermissionService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 安全元数据源
 * 用于根据请求路径获取该路径对应的权限信息
 */
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    
    /**
     *  AntPathMatcher 是 Spring Framework 中用于进行路径匹配的工具类，通常用于匹配 URL 路径。
     */
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    
    // 注入SysPermissionService，用于查询权限信息
    @Resource
    private SysPermissionService sysPermissionService;
    
    /**
     * 根据请求路径获取该路径对应的权限信息
     *
     * @param object 请求对象，通常是FilterInvocation类型
     * @return 该路径对应的权限信息，如果路径没有配置权限，则返回null
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取请求地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        
        // 查询具体某个接口的权限
        List<SysPermission> permissionList =  sysPermissionService.selectListByPath(requestUrl);
        
        // 如果权限列表为空，则表示该请求接口可以任意访问
        if (permissionList == null || permissionList.isEmpty()) {
            return null;
        }
        
        // 构造权限字符串数组
        String[] attributes = new String[permissionList.size()];
        for (int i = 0; i < permissionList.size(); i++) {
            attributes[i] = permissionList.get(i).getPermissionCode();
        }
        
        // 将权限字符串数组转换为Spring Security需要的ConfigAttribute集合
        return SecurityConfig.createList(attributes);
    }
    
    // 获取所有的配置属性，这里暂时返回null
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
    
    // 是否支持指定的类，这里返回true表示支持任意类型的类
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
