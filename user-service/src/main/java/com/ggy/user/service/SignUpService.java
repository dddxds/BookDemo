package com.ggy.user.service;

import com.ggy.config.AjaxResult;
import com.ggy.config.Result;
import com.ggy.pojo.User;
import com.ggy.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SignUpService {
    @Autowired
    private UserMapper userMapper;

    public AjaxResult SignUp(User user){
        if (userMapper.insert(user)!=0) {
            return Result.success();
        }else {
            return Result.error();
        }
    }
}
