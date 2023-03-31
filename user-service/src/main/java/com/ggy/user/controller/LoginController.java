package com.ggy.user.controller;

import com.ggy.config.AjaxResult;
import com.ggy.pojo.User;
import com.ggy.user.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 *  @title: LoginController
 *  @author: gaoguiyun
 *  @version:   1.0.0
 *  @create: 2023/2/7 10:54
 ***/
@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult Login(@RequestBody User user){
       return loginService.Login(user);

    }
}
