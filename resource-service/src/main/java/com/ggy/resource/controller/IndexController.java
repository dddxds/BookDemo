package com.ggy.resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *  @title: IndexController
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023/2/9 14:37
 ***/
@RestController
public class IndexController {
    @RequestMapping("/")
    public String index(){
        System.out.println("请求发到了这里");

        return "index";
    }
}
