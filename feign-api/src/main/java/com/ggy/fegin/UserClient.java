package com.ggy.fegin;


import com.ggy.config.AjaxResult;
import com.ggy.pojo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;



@FeignClient(value="user-service",path = "/user")
public interface UserClient{
    //通过id查询用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public AjaxResult findById(@PathVariable("id") Long id);
    //获取所有用户
    @RequestMapping(value = "/findall",method = RequestMethod.GET)
    public AjaxResult findAllUser();
    //注册
    @RequestMapping(value = "/registration",method = RequestMethod.POST)
    public AjaxResult registration(@RequestBody User user);
    @RequestMapping(value = "/uploadresource/{id}",method = RequestMethod.GET)
    public AjaxResult uploadResource(@PathVariable("id")Long id);

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult Login(@RequestBody User user);

    @RequestMapping(value = "/updateuserlike",method = RequestMethod.POST)
    AjaxResult updateInfo(@RequestBody User user);
    //注销
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    AjaxResult delUser(@PathVariable("id") Long id);
    //更改积分 id::分数
    @RequestMapping(value = "/score",method = RequestMethod.POST)
    AjaxResult scoreChange(@RequestBody String msg);

    @RequestMapping(value = "/getcollect",method = RequestMethod.GET)
    AjaxResult getCollect(@RequestParam("id") Long id);
    @RequestMapping(value = "/delcollect",method = RequestMethod.DELETE)
    AjaxResult delCollect(@RequestParam("id") Long id);
    @RequestMapping(value = "/addcollect",method = RequestMethod.POST)
    AjaxResult addCollect(@RequestBody Collect collect);

    @RequestMapping(value = "/gethistory",method = RequestMethod.GET)
    AjaxResult getHistory(@RequestParam("id") Long id);
    @RequestMapping(value = "/delhistory",method = RequestMethod.DELETE)
    AjaxResult delHistory(@RequestParam("id") Long id);
    @RequestMapping(value = "/addhistory",method = RequestMethod.POST)
    AjaxResult addHistory(@RequestBody History history);

    @RequestMapping(value = "/getnotice",method = RequestMethod.GET)
    AjaxResult getNotice(@RequestParam("id") Long id);
    @RequestMapping(value = "/delnotice",method = RequestMethod.DELETE)
    AjaxResult delNotice(@RequestParam("id") Long id);
    @RequestMapping(value = "/addnotice",method = RequestMethod.POST)
    AjaxResult addNotice(@RequestBody Notice notice);

    @RequestMapping(value = "/manage/login",method = RequestMethod.POST)
    public AjaxResult AdminLogin(@RequestBody Admin user);




}
