package com.example.desensitize.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.desensitize.entity.UserEntity;

public interface UserService extends IService<UserEntity> {

    void getAllUserByUserName(String userName);

}
