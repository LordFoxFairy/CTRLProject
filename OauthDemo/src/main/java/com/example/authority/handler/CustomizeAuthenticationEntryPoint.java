package com.example.authority.handler;

import cn.hutool.json.JSONUtil;
import com.example.authority.common.entity.ResponseEntity;
import com.example.authority.common.vo.ResultVO;
import com.example.authority.enums.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理匿名用户访问无权限资源时的异常
 * 当匿名用户尝试访问受保护资源但未进行身份验证时，此类将返回适当的错误响应。
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    // 实现接口方法
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        // 返回适当的错误响应
        ResponseEntity<Object> result = ResultVO.fail(ResultCode.USER_NOT_LOGIN);
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(result));
    }
}

