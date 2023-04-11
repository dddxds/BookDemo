package com.ggy.user.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ggy.pojo.User;
import com.ggy.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public boolean updateUser(User user) {
        user.setModifyTime(LocalDateTime.now());
        int i = userMapper.updateById(user);
        if (i != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean del(Long id) {
        int i = userMapper.deleteById(id);
        if (i != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean registration(User user) {
        user.setCreatedTime(LocalDateTime.now());
        user.setCreatedUser("admin");
        user.setModifyUser("admin");
        user.setModifyTime(LocalDateTime.now());
        if (user.getPicture() == null) {
            user.setPicture("@/assets/logo.png");
        }
        int i = userMapper.insert(user);
        if (i != 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    public User findById(long id) {
        return userMapper.selectById(id);
    }

    public int uploadResource(Long id) {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();

        userUpdateWrapper.setSql("'upload'='upload'+1").eq("id",id);
        return userMapper.update(null,userUpdateWrapper);
    }
}
