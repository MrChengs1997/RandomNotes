package com.shiro.mapper;

import com.shiro.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    public Users queryUser(String user);

}
