package com.example.authority.handler;

import cn.hutool.json.JSONUtil;
import com.example.authority.common.entity.ResponseEntity;
import com.example.authority.common.vo.ResultVO;
import com.example.authority.entity.SysUser;
import com.example.authority.service.SysUserService;
import com.example.authority.utils.JwtUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 处理用户登录成功后的逻辑
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    
    @Resource
    private SysUserService sysUserService;
    
    @Resource
    private JwtUtils jwtUtils;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        // 获取当前登录用户的 UserDetails 对象
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        // 根据用户名查询数据库获取用户信息
        SysUser sysUser = sysUserService.selectByName(userDetails.getUsername());
        
        // 更新用户表的上次登录时间、更新人、更新时间等字段
        sysUser.setLastLoginTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setUpdateUser(sysUser.getId());
        sysUserService.updateById(sysUser);
        
        // 生成JWT，并放置到请求头中
        String jwt = jwtUtils.generateToken(authentication.getName());
        httpServletResponse.setHeader(jwtUtils.getHeader(), jwt);
        
        //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
        //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展
        
        // 返回 JSON 格式的成功信息给前端
        ResponseEntity<Object> result = ResultVO.success();
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(result));
    }
}

