package com.ggy.user.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ggy.config.AjaxResult;
import com.ggy.config.HttpStatus;
import com.ggy.config.Result;
import com.ggy.pojo.Admin;
import com.ggy.pojo.User;
import com.ggy.user.mapper.AdminMapper;
import com.ggy.user.mapper.UserMapper;
import com.ggy.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AdminMapper adminMapper;

    public AjaxResult Login(User user) {
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("uNumber", user.getUNumber()));
        boolean equals = false;
        if (user1 != null) {
            equals = user1.getUPwd().equals(user.getUPwd());
            if (equals) {
                // 生成令牌返回给用户
                Map<String,Object> info = new HashMap<>();
                info.put("username",user1.getId());
                info.put("role","ROLE_ADMIN");
                String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(info), null);
                user1.setUPwd("");
                return Result.success(200,token,user1);
            } else {
                return Result.error("账号或密码错误");
            }
        } else {
            return Result.error("账号或密码错误");
        }
    }
    public AjaxResult AdminLogin(Admin user) {
        Admin user1 = adminMapper.selectOne(new QueryWrapper<Admin>().eq("uNumber", user.getUNumber()));
        boolean equals=false;
        if (user1 != null) {
            equals = user1.getUPwd().equals(user.getUPwd());
            if (equals) {
                // 生成令牌返回给用户
                Map<String,Object> info = new HashMap<>();
                info.put("username",user1.getId());
                info.put("role","ROLE_ADMIN");
                String token = JwtUtil.createJWT(UUID.randomUUID().toString(), JSON.toJSONString(info), null);
                user1.setUPwd("");
                return Result.success(200,token,user1);
            } else {
                return Result.error("账号或密码错误");
            }
        } else {
            return Result.error("账号或密码错误");
        }
    }


}
