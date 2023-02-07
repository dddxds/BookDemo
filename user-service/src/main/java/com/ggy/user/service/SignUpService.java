package com.ggy.user.service;

import com.ggy.pojo.User;
import com.ggy.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    private UserMapper userMapper;

    public int SignUp(User user){
        return userMapper.insert(user);
    }
}
