package com.example.authority.filter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.authority.common.RedisConstant;
import com.example.authority.exception.CaptchaException;
import com.example.authority.handler.CustomizeAuthenticationFailureHandler;
import com.example.authority.utils.RedisLock;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/*
    CaptchaFilter继承了OncePerRequestFilter抽象类，该抽象类在每次请求时只执行一次过滤，
    即它的作用就是保证一次请求只通过一次filter,而不需要重复执行。
    CaptchaFilter需要重写其doFilterInternal方法来自定义处理逻辑
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {
    
    @Resource
    private RedisLock redisLock;
    
    @Resource
    private CustomizeAuthenticationFailureHandler loginFailureHandler;
    
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        String url = httpServletRequest.getRequestURI();
        if ("/login".equals(url) && httpServletRequest.getMethod().equals("POST")) {
            // 校验验证码
            try {
                validate(httpServletRequest);
            } catch (CaptchaException e) {
                
                // 交给认证失败处理器
                loginFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
    
    // 校验验证码逻辑
    private void validate(HttpServletRequest httpServletRequest) {
        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("userKey");
        
        String lockKey = String.format(RedisConstant.CAPTCHA_KEY, key);
        
        if (StringUtils.isNotEmpty(code) || StringUtils.isNotEmpty(key)) {
            throw new CaptchaException("验证码错误");
        }
        
        if (!Objects.equals(code, redisLock.get(lockKey))) {
            throw new CaptchaException("验证码错误");
        }
        
        // 若验证码正确，执行以下语句
        // 一次性使用
        redisLock.del(lockKey);
    }
}

