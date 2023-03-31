package com.ggy.user.controller;


import com.ggy.config.AjaxResult;
import com.ggy.config.Result;
import com.ggy.pojo.User;
import com.ggy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public int uploadResource(Long status){
        int i=0;
//        if (status!=0){
//           userService
//        }
        return i;
    }

    @RequestMapping(value = "/updateuserlike",method = RequestMethod.POST)
    public AjaxResult updateLike(User user){
        if (userService.updateUser(user)){
            return Result.success();
        }else {
            return Result.error();
        }
    }
}
