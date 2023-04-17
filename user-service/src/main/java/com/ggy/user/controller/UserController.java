package com.ggy.user.controller;


import com.ggy.config.AjaxResult;
import com.ggy.config.Result;
import com.ggy.pojo.*;
import com.ggy.user.service.LoginService;
import com.ggy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult Login(@RequestBody User user){
//        System.out.println(user.toString());
        return loginService.Login(user);
    }

    /***
     *
     * @author GaoGuiYun
     * @date 2023/2/7 15:37
     * @param id
     * @return int
     * 获取用户信息 然后将用户的上传数加一
     */
    @RequestMapping(value="/uploadresource/{id}",method = RequestMethod.GET)
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

    //通过id查询用户  评论用
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AjaxResult findById(@PathVariable("id") Long id){
        User byId = userService.findById(id);
        byId.setUPwd("");
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
    @RequestMapping(value = "/del/{id}",method = RequestMethod.DELETE)
    AjaxResult delUser(@PathVariable("id") Long id){
        if(userService.del(id)){
            return Result.success();
        }else {
            return Result.error();
        }
    };
    @RequestMapping(value = "/updateintegral",method = RequestMethod.POST)
    AjaxResult updateInfo(@RequestBody User user){

        if(userService.updateUser(user)){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @RequestMapping(value = "/score",method = RequestMethod.POST)
    AjaxResult scoreChange(@RequestBody String msg){
        String[] msgs = msg.split("::");

        Long id= Long.valueOf(msgs[0]);
        Integer count= Integer.valueOf(msgs[1]);
        if(userService.scoreChange(id,count)!=0){
            return Result.success();
        }else {
            return Result.error();
        }
    }
    @RequestMapping(value = "/getcollect",method = RequestMethod.GET)
    AjaxResult getCollect(@RequestParam("id") Long id){
        List<Collect> collect = userService.getCollect(id);
        if(collect!=null){
            return Result.success(collect);
        }else {
            return Result.error("未查询到");
        }

    }
    @RequestMapping(value = "/delcollect",method = RequestMethod.DELETE)
    AjaxResult delCollect(@RequestParam("id") Long id) {
        int i = userService.delCollect(id);
        if(i!=0){
            return Result.success();
        }else {
            return Result.error("删除失败");
        }
    }
    @RequestMapping(value = "/addcollect",method = RequestMethod.POST)
    AjaxResult addCollect(@RequestBody Collect collect) {
        int i = userService.addCollect(collect);
        if(i!=0){
            return Result.success();
        }else {
            return Result.error();
        }

    }

    @RequestMapping(value = "/gethistory",method = RequestMethod.GET)
    AjaxResult getHistory(@RequestParam("id") Long id) {
        List<History> collect = userService.getHistory(id);
        if(collect!=null){
            return Result.success(collect);
        }else {
            return Result.error("未查询到");
        }
    }
    @RequestMapping(value = "/delhistory",method = RequestMethod.DELETE)
    AjaxResult delHistory(@RequestParam("id") Long id) {
        int i = userService.delHistory(id);
        if(i!=0){
            return Result.success();
        }else {
            return Result.error("删除失败");
        }
    }
    @RequestMapping(value = "/addhistory",method = RequestMethod.POST)
    AjaxResult addHistory(@RequestBody History history) {
        int i = userService.addHistory(history);

        if(i!=0){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @RequestMapping(value = "/getnotice",method = RequestMethod.GET)
    AjaxResult getNotice() {
        List<Notice> notice = userService.getNotice();
        if(notice!=null){
            return Result.success(notice);
        }else {
            return Result.error("未查询到");
        }
    }
    @RequestMapping(value = "/delnotice",method = RequestMethod.DELETE)
    AjaxResult delNotice(@RequestParam("id") Long id) {
        int i = userService.delNotice(id);
        if(i!=0){
            return Result.success();
        }else {
            return Result.error("删除失败");
        }
    }
    @RequestMapping(value = "/addnotice",method = RequestMethod.POST)
    AjaxResult addNotice(@RequestBody Notice notice) {
        int i = userService.addNotice(notice);
        if(i!=0){
            return Result.success();
        }else {
            return Result.error();
        }
    }

    @RequestMapping(value = "/manage/login",method = RequestMethod.POST)
    public AjaxResult AdminLogin(@RequestBody Admin user) {
        return loginService.AdminLogin(user);
    }
}
