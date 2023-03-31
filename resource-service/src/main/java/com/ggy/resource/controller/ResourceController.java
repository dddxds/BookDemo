package com.ggy.resource.controller;

import com.ggy.config.AjaxResult;
import com.ggy.config.Result;
import com.ggy.pojo.Comment;
import com.ggy.pojo.Resource;
import com.ggy.resource.service.CommentService;
import com.ggy.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private CommentService commentService;

    //发布资源
    @RequestMapping(value = "/public",method = RequestMethod.POST)
    public AjaxResult insertResource(@RequestBody Resource resource){
        int i=resourceService.uploadResource(resource);
        if(i!=0){
            return Result.success();
        }
        else {
            return Result.error();
        }
//        return Result.success(resource);

    }
    //删除资源
    @RequestMapping(value = "/delete" ,method= RequestMethod.DELETE)
    public AjaxResult deleteResource(@RequestParam Long id){
        int i=resourceService.DeleteResource(id);

        if(i!=0){
            return Result.success();
        }
        else {
            return Result.error();
        }

    };
    @RequestMapping (value = "/update",method = RequestMethod.PUT)
    public AjaxResult updateResource(@RequestBody Resource resource){
        int i=resourceService.UpdateResource(resource);
       // return Result.success(resource);

        if(i!=0){
            return Result.success();
        }
        else {
            return Result.error();
        }


    };

    //获取资源详情
    @RequestMapping(value = "/getresource/{id}", method = RequestMethod.GET)
    public AjaxResult findResourceById(@PathVariable("id")Long id){
        System.out.println("到了控制层,"+"id:"+id);
        Resource resource = resourceService.FindById(id);
        if(resource!=null){
            return Result.success(resource);
        }
        else {
            return Result.error();
        }
    };

    //获取资源列表 后期做分页查询  根据各种条件返回
    @RequestMapping(value = "/getresources",method = RequestMethod.GET)
    public AjaxResult findAllResources(){
        List<Resource> resources = resourceService.FindAll();
        if(resources!=null){
            return Result.success(resources);
        }else {
            return Result.error();
        }


    };

    //获取某个人发布的资源
    @RequestMapping(value = "/getbyuser/{id}",method = RequestMethod.GET)
    public AjaxResult findResourcesByUser(@PathVariable("id")Long id){
        List<Resource> resources = resourceService.FindByUser(id);
        if(resources!=null){
            return Result.success(resources);
        }else {
            return Result.error();
        }
    };

    //获取某类资源
    @RequestMapping(value = "/getbytype/{type}",method = RequestMethod.GET)
    public AjaxResult findResourcesByType(@PathVariable("type")Long id) {
        List<Resource> resources = resourceService.FindByType(id);
        if(resources!=null){
            return Result.success(resources);
        }else {
            return Result.error();
        }
    };

    //----------------------------------------------------------------------------------


    //获取资源下评论
    @RequestMapping(value = "/getcomments/{id}",method = RequestMethod.GET)
    public AjaxResult getComments(@PathVariable("id")Long id){
        List<Comment> comments=commentService.getComments(id);


        if (comments!=null){
            return Result.success(comments);

        }else {
            return Result.error();
        }


    }

    //添加评论
    @RequestMapping(value = "/addcomment",method = RequestMethod.POST)
    public AjaxResult addComment(@RequestBody Comment comment){
        int i =commentService.addComment(comment);
        if (i!=0){
            return Result.success();
        }else {
            return Result.error();
        }



    };

//    //对评论点赞   应该只获取数量与评论id的  不过暂且这样
//    @RequestMapping(value = "/up",method = RequestMethod.POST)
//    public AjaxResult upComment(@RequestBody Comment comment){
//        int i=commentService.upComment(comment);
//
//        if (i!=0){
//            return Result.success();
//        }else {
//            return Result.error();
//        }
//    };

    //收藏资源  用户信息与资源信息
    @RequestMapping(value = "/collect" ,method = RequestMethod.POST)
    public AjaxResult collectResource(@RequestBody Resource resource){
        int i=resourceService.collectResource(resource);
        if (i!=0){
            return Result.success();
        }else {
            return Result.error();
        }
    };
    //历史记录
    @RequestMapping(value = "/addhistory" ,method = RequestMethod.POST)
    public AjaxResult historyResource(@RequestBody Resource resource){
        int i=resourceService.historyResource(resource);
        if (i!=0){
            return Result.success();
        }else {
            return Result.error();
        }
    };
    //对资源点赞
    @RequestMapping(value = "/up",method = RequestMethod.POST)
    public AjaxResult upResource(@RequestParam String msg){
        String [] msgs=msg.split("::");
        
        if (resourceService.upResource(msgs)){
            return Result.success();
        }else {
            return Result.error();
        }
    };
}
