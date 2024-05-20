package com.example.authority.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.authority.common.entity.ResponseEntity;
import com.example.authority.common.vo.ResultVO;
import com.example.authority.entity.SysUser;
import com.example.authority.service.SysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RestController
public class SysUserController {
    
    @Resource
    private SysUserService sysUserService;
    
    
    @GetMapping("/getUser")
    public List<SysUser> getUser(){
        return sysUserService.selectList();
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/logout")
    public ResponseEntity<Object> logout() {
        // 执行登出操作，清除当前用户的认证信息
        SecurityContextHolder.clearContext();
        return ResultVO.success();
    }
    
    
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:user:list')")
    public ResponseEntity<Object> list(String username) {
        return ResultVO.success();
    }
    
    
    
}
