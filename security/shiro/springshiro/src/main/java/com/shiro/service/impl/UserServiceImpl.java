package com.shiro.service.impl;

import com.shiro.mapper.UserMapper;
import com.shiro.pojo.Users;
import com.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    @Override
    public Users queryUser(String user) {
        return mapper.queryUser(user);
    }
}
