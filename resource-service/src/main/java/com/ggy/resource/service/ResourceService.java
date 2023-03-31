package com.ggy.resource.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ggy.fegin.UserClient;
import com.ggy.pojo.Collect;
import com.ggy.pojo.History;
import com.ggy.pojo.Resource;
import com.ggy.resource.mapper.CollectMapper;
import com.ggy.resource.mapper.HistoryMapper;
import com.ggy.resource.mapper.ResourceMapper;
import com.ggy.resource.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private LikedService likedService;
    @Autowired
    private RedisService redisService;

    @Autowired
    private UserClient userClient;

    public Resource FindById(Long id){
        return resourceMapper.selectById(id.toString());
    }

    public int uploadResource(Resource resource){
        //业务处理，存储数据
        resource.setCreatedTime(LocalDateTime.now());
        resource.setModifyTime(LocalDateTime.now());
        resource.setStatus(1l);
        userClient.uploadResource(resource.getCreateUserId());

        return resourceMapper.insert(resource);
    }
    public int DeleteResource(Long id) {
        return resourceMapper.deleteById(id);
    }

    public List<Resource> FindAll() {
        return resourceMapper.selectList(null);
    }

    public int UpdateResource(Resource resource) {
        return resourceMapper.update(resource,null);
    }

    public List<Resource> FindByUser(Long id) {


        return resourceMapper.selectList(new QueryWrapper<Resource>().eq("createUserId",String.valueOf(id)));

    }

    public List<Resource> FindByType(Long id) {
        String sid=String.valueOf(id);
        return resourceMapper.selectList(new QueryWrapper<Resource>().eq("type",sid));
    }
    //收藏资源 获取当前用户以及资源的id 添加到收藏表
    public int collectResource(Resource resource) {
        Collect collect=new Collect();
        collect.setUserId(resource.getCreateUserId());
        collect.setResourceId(resource.getId());
        return collectMapper.insert(collect);
    }
    //历史记录
    public int historyResource(Resource resource) {
        History history = new History();
        history.setUserId(resource.getCreateUserId());
        history.setResourceId(resource.getId());
        return historyMapper.insert(history);
    }
    //获取这个数组 点赞id 被点赞id 类型 状态
    public boolean upResource(String [] resource) {
        //之后再弄细分

        return redisService.saveLiked2Redis(resource[0], resource[1]);
    }
}
