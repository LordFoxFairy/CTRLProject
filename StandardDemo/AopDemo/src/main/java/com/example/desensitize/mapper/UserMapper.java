package com.example.desensitize.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.desensitize.entity.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<UserEntity> {
}
