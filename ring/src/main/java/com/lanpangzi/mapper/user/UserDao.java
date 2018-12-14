package com.lanpangzi.mapper.user;

import com.lanpangzi.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("SELECT username FROM users WHERE uid=#{uid} LIMIT 0,1")
    User getUserDao(Integer uid);
}
