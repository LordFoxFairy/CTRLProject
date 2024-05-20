package com.example.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.authority.entity.SysRequestPathPermissionRelation;
import com.example.authority.mapper.SysRequestPathPermissionRelationMapper;
import com.example.authority.service.SysRequestPathPermissionRelationService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class SysRequestPathPermissionRelationServiceImpl extends ServiceImpl<SysRequestPathPermissionRelationMapper, SysRequestPathPermissionRelation> implements SysRequestPathPermissionRelationService {
}
