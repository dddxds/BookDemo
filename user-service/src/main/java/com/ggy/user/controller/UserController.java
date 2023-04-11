package com.ggy.user.controller;


import com.ggy.config.AjaxResult;
import com.ggy.config.Result;
import com.ggy.pojo.User;
import com.ggy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /***
     *
     * @author GaoGuiYun
     * @date 2023/2/7 15:37
     * @param status
     * @return int
     * 获取用户信息 然后将用户的上传数加一
     */
    @RequestMapping("/uploadresource")
    public AjaxResult uploadResource(@PathVariable("id") Long id){
        int i=0;
//        if (status!=0){
//           userService
//        }
        userService.uploadResource(id);
        return Result.success();
    }

    @RequestMapping(value = "/updateuserlike",method = RequestMethod.POST)
    public AjaxResult updateLike(@RequestBody User user){
        if (userService.updateUser(user)){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    //通过id查询用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AjaxResult findById(@PathVariable("id") Long id){
        User byId = userService.findById(id);
        if(byId!=null){
            return Result.success(byId);
        }else {
            return Result.error();
        }
    };
    //获取所有用户
    @RequestMapping(value = "/findall",method = RequestMethod.GET)
    public AjaxResult findAllUser(){
        List<User> all = userService.findAll();
        if(all!=null){
            return Result.success(all);
        }else {
            return Result.error();
        }

    };
    //注册
    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public AjaxResult registration(@RequestBody User user){
        System.out.println(user);
       if(userService.registration(user)) {

           return Result.success();
       }else {
           return Result.error();
       }
    };

    //注销
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    AjaxResult delUser(@PathVariable("id") Long id){
        if(userService.del(id)){
            return Result.success();
        }else {
            return Result.error();
        }
    };
}
