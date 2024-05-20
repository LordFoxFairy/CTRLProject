package com.example.desensitize.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.desensitize.annotation.Idempotent;
import com.example.desensitize.service.IdempotentKeyResolver;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

/**
 * 默认幂等 Key 解析器，使用方法名 + 方法参数，组装成一个 Key
 * <p>
 * 为了避免 Key 过长，使用 MD5 进行“压缩”
 *
 */
@Service
public class DefaultIdempotentKeyResolver implements IdempotentKeyResolver {
    
    @Override
    public String resolver(JoinPoint joinPoint, Idempotent idempotent) {
        String methodName = joinPoint.getSignature().toString();
        String argsStr = StrUtil.join(",", joinPoint.getArgs());
        return SecureUtil.md5(methodName + argsStr);
    }
    
}
