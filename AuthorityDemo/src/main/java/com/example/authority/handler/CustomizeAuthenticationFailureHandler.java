package com.example.authority.handler;

import cn.hutool.json.JSONUtil;
import com.example.authority.common.entity.ResponseEntity;
import com.example.authority.common.vo.ResultVO;
import com.example.authority.enums.ResultCode;
import com.example.authority.exception.CaptchaException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理用户登录失败后的逻辑
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        // 返回JSON格式的失败信息给前端
        ResponseEntity<Object> result;
        if (e instanceof AccountExpiredException) {
            // 账号过期
            result = ResultVO.fail(ResultCode.USER_ACCOUNT_EXPIRED);
        } else if (e instanceof BadCredentialsException) {
            // 密码错误
            result = ResultVO.fail(ResultCode.USER_CREDENTIALS_ERROR);
        } else if (e instanceof CredentialsExpiredException) {
            // 密码过期
            result = ResultVO.fail(ResultCode.USER_CREDENTIALS_EXPIRED);
        } else if (e instanceof DisabledException) {
            // 账号不可用
            result = ResultVO.fail(ResultCode.USER_ACCOUNT_DISABLE);
        } else if (e instanceof LockedException) {
            // 账号锁定
            result = ResultVO.fail(ResultCode.USER_ACCOUNT_LOCKED);
        } else if (e instanceof InternalAuthenticationServiceException) {
            // 用户不存在
            result = ResultVO.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        } else if (e instanceof CaptchaException) {
            // 验证码错误
            result = ResultVO.fail(ResultCode.USER_INVALID_VERIFICATION_CODE);
        }else {
            // 其他错误
            result = ResultVO.fail(ResultCode.COMMON_FAIL);
        }
        
        // 设置响应的编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        // 将结果写入响应中返回给前端
        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(result));
    }
}
