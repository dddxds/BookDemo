package com.ggy.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ggy.config.AjaxResult;
import com.ggy.config.Result;
import com.ggy.pojo.User;
import com.ggy.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;

    public AjaxResult Login(User user){
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("uName",user.getUName()));

        if(user1.getUPwd()==user.getUPwd()){
            return Result.success();
        }else {
            return Result.error();
    }
    }
}
