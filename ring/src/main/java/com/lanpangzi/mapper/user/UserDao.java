package com.lanpangzi.mapper.user;

import com.lanpangzi.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    User getUserDao(Integer uid);
}
