package com.ggy.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ggy.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
