package com.example.authority.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.authority.entity.SysUser;
import com.example.authority.mapper.SysUserMapper;
import com.example.authority.service.SysUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    
    @Override
    public SysUser selectByName(String userName) {
        
        if(Objects.isNull(userName)){
            return null;
        }
        
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserName, userName);
        return this.baseMapper.selectOne(queryWrapper);
    }
    
    @Override
    public List<SysUser> selectList() {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        return this.baseMapper.selectList(queryWrapper);
    }
}
