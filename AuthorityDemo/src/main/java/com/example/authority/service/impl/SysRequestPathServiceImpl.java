package com.example.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.authority.entity.SysRequestPath;
import com.example.authority.mapper.SysRequestPathMapper;
import com.example.authority.service.SysRequestPathService;
import org.springframework.stereotype.Service;

@Service
public class SysRequestPathServiceImpl extends ServiceImpl<SysRequestPathMapper, SysRequestPath> implements SysRequestPathService {
}
