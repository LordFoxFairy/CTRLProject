package com.example.authority.filter;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

/**
 * 权限拦截器
 * 实现了Spring Security的AbstractSecurityInterceptor，用于拦截请求并进行权限校验
 */
@Service
public class CustomizeAbstractSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    
    // 注入安全元数据源，用于获取请求路径对应的权限信息
    @Resource
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
    
    // 注入自定义的权限决策管理器
    @Resource
    public void setMyAccessDecisionManager(CustomizeAccessDecisionManager accessDecisionManager) {
        super.setAccessDecisionManager(accessDecisionManager);
    }
    
    // 返回被拦截对象的类，这里是 FilterInvocation.class
    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }
    
    // 返回安全元数据源
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
    
    // 进行请求过滤
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(fi);
    }
    
    // 执行拦截器
    public void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
        // 调用Spring Security的权限校验流程
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try {
            // 执行下一个拦截器
            filterInvocation.getChain().doFilter(
                    filterInvocation.getRequest(),
                    filterInvocation.getResponse()
            );
        } finally {
            super.afterInvocation(token, null);
        }
    }
}