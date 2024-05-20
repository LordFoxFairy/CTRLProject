package com.example.authority.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisLock {
    
    @Resource
    private StringRedisTemplate redisTemplate;
    
    public String get(String lockKey) {
        return redisTemplate.opsForValue().get(lockKey);
    }
    
    public void set(String lockKey, String value) {
        redisTemplate.opsForValue().set(lockKey, value);
    }
    
    public void del(String lockKey) {
        String value = redisTemplate.opsForValue().get(lockKey);
        if (StringUtils.isNotEmpty(value)) {
            redisTemplate.delete(lockKey);
        }
    }
}
