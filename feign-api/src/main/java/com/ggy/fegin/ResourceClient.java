package com.ggy.fegin;


import com.ggy.config.AjaxResult;

import com.ggy.pojo.Comment;
import com.ggy.pojo.Resource;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.*;



@FeignClient(value = "resource-service" ,path = "/resource")
public interface ResourceClient{

    @RequestMapping(value = "/")
    public String index();
    //发布资源
    @RequestMapping(value = "/public",method = RequestMethod.POST)
    public AjaxResult insertResource(@RequestBody Resource resource);
    //删除资源
    @RequestMapping(value = "/delete" ,method= RequestMethod.DELETE)
    public AjaxResult deleteResource(@RequestParam Long id);
    @RequestMapping (value = "/update",method = RequestMethod.PUT)
    public AjaxResult updateResource(@RequestBody Resource resource);

    //获取资源详情
    @RequestMapping(value = "/getresource/{id}", method = RequestMethod.GET)
    public AjaxResult findResourceById(@PathVariable("id")Long id);

    //获取资源列表 后期做分页查询  根据各种条件返回
    @RequestMapping(value = "/getresources",method = RequestMethod.GET)
    public AjaxResult findAllResources();
    //获取某个人发布的资源
    @RequestMapping(value = "/getbyuser/{id}",method = RequestMethod.GET)
    public AjaxResult findResourcesByUser(@PathVariable("id")Long id);

    //获取某类资源
    @RequestMapping(value = "/getbytype",method = RequestMethod.GET)
    public AjaxResult findResourcesByType(@PathVariable("type")String type);

    //获取资源下评论
    @RequestMapping(value = "/getcomments",method = RequestMethod.GET)
    public AjaxResult getComments(@PathVariable("id")Long id);

    //添加评论
    @RequestMapping(value = "/addcomment",method = RequestMethod.POST)
    public AjaxResult addComment(@RequestBody Comment comment);

//    //对评论点赞   应该只获取数量与评论id的  不过暂且这样
//    @RequestMapping(value = "/up",method = RequestMethod.POST)
//    public AjaxResult upComment(@RequestBody Comment comment);

    //收藏资源  用户信息与资源信息
    @RequestMapping(value = "/collect" ,method = RequestMethod.POST)
    public AjaxResult collectResource(@RequestBody Resource resource);

    //历史记录
    @RequestMapping(value = "/addhistory" ,method = RequestMethod.POST)
    public AjaxResult historyResource(@RequestBody Resource resource);

    //对资源点赞
    @RequestMapping(value = "/up",method = RequestMethod.POST)
    public AjaxResult upResource(@RequestBody String msg);
    @RequestMapping(value = "/getresourcespage",method = RequestMethod.POST)
    public AjaxResult findResourcesByPage(@RequestBody String s);










}
