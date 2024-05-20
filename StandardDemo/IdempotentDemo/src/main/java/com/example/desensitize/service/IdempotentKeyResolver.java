package com.example.desensitize.service;

import com.example.desensitize.annotation.Idempotent;
import org.aspectj.lang.JoinPoint;

public interface IdempotentKeyResolver {
    String resolver(JoinPoint joinPoint, Idempotent idempotent);
}
