package com.ggy.user.controller;

import com.ggy.config.AjaxResult;
import com.ggy.config.Result;
import com.ggy.pojo.User;
import com.ggy.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/***
 *  @title: LoginController
 *  @author: gaoguiyun
 *  @version:   1.0.0
 *  @create: 2023/2/7 10:54
 ***/
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult Login(@RequestBody User user){
//        System.out.println(user.toString());
       return loginService.Login(user);


    }
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public AjaxResult Login(@RequestBody String user){
        System.out.println(user);
        return Result.success(user);

    }
}
