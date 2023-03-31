package com.ggy.user.service;


import com.ggy.pojo.User;
import com.ggy.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public boolean updateUser(User user){
        int i=userMapper.updateById(user);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }
}
