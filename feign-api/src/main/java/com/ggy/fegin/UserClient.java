package com.ggy.fegin;


import com.ggy.config.AjaxResult;
import com.ggy.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;



@FeignClient(value="user-service",path = "/user")
public interface UserClient{
    //通过id查询用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AjaxResult findById(@PathVariable("id") long id);
    //获取所有用户
    @RequestMapping(value = "/findall",method = RequestMethod.GET)
    public AjaxResult findAllUser();
    //注册
    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public AjaxResult registration(@RequestBody User user);
    @RequestMapping(value = "/uploadresource",method = RequestMethod.GET)
    public AjaxResult uploadResource(@PathVariable("id")Long id);

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult Login(@RequestBody User user);

    @RequestMapping(value = "/updateuserlike",method = RequestMethod.POST)
    AjaxResult updateInfo(@RequestBody User user);
    //注销
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    AjaxResult delUser(@PathVariable("id") Long id);
}
