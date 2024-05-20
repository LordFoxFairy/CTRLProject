package com.example.authority.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.authority.entity.SysRequestPathPermissionRelation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysRequestPathPermissionRelationMapper extends BaseMapper<SysRequestPathPermissionRelation> {
}
