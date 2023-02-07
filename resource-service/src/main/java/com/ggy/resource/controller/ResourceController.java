package com.ggy.resource.controller;

import com.ggy.pojo.Resource;
import com.ggy.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    //发布资源
    @PostMapping("/public")
    public int insertResource(@RequestBody Resource resource){
        return 0;
    };
    //删除资源
    @RequestMapping(value = "/delete" ,method= RequestMethod.DELETE)
    public int deleteResource(@RequestParam Long id){
        return 0;
    };
    @PostMapping ("/update")
    public int updateResource(@RequestBody Resource resource){
        return 0;
    };

    //获取资源详情
    @RequestMapping(value = "/getresource/{id}", method = RequestMethod.GET)
    public Resource findResourceById(@PathVariable("id")long id){
        System.out.println("到了控制层,"+"id:"+id);
        Resource resource = resourceService.FindById(id);

        return resource;
    };

    //获取资源列表 后期做分页查询  根据各种条件返回
    @GetMapping("/getresources")
    public List<Resource> findAllResources(){
        return null;
    };

    //获取某个人发布的资源
    @GetMapping("/getbyuser/{id}")
    public List<Resource> findResources(@PathVariable("id")long id){
        return null;
    };

    //获取某类资源
    @GetMapping("/getbytype/{type}")
    public List<Resource> findResources(@PathVariable("type")String id) {

        return null;
    };
}
