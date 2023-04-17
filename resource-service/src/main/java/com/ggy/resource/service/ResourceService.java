package com.ggy.resource.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

    public Resource FindById(Long id) {
        return resourceMapper.selectById(id.toString());
    }

    public int uploadResource(Resource resource) {
        //业务处理，存储数据
        resource.setCreatedTime(LocalDateTime.now());
        resource.setModifyTime(LocalDateTime.now());
        resource.setStatus(1l);
        userClient.uploadResource(resource.getCreateUserId());

        return resourceMapper.insert(resource);
    }
     /***
      *   删除需要联动删除，其他表比如评论表，点赞，发布数量等
      *   收藏表直接删除  其他的改状态码
      * @author GaoGuiYun
      * @date 2023-04-11 15:42
      * @param
      * @return
      */
    public int DeleteResource(Long id) {



        return resourceMapper.deleteById(id);
    }

    public List<Resource> FindAll() {
        return resourceMapper.selectList(null);
    }

    //分页查询 ，条件可以根据 时间  热度  分类等

    public IPage FindResourceByPage(IPage ipage, String[] s) {
        //对S进行处理

        LambdaQueryWrapper<Resource> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!s[1].equals("") ||s[1]!=null) {
            if (s[2].equals("time")) {
                objectLambdaQueryWrapper.orderBy(true, Boolean.parseBoolean(s[3]), Resource::getCreatedTime).eq(Resource::getResourceClass, s[1]);
            } else if (s[2].equals("ulike")) {
                objectLambdaQueryWrapper.orderBy(true, Boolean.parseBoolean(s[3]), Resource::getUlike).eq(Resource::getResourceClass, s[1]);
            }
            else if (s[2].equals("see")) {
                objectLambdaQueryWrapper.orderBy(true, Boolean.parseBoolean(s[3]), Resource::getSee).eq(Resource::getResourceClass, s[1]);
            }
            else {
                objectLambdaQueryWrapper = null;
            }
        } else {
            if (s[2].equals("time")) {
                objectLambdaQueryWrapper.orderBy(true, Boolean.parseBoolean(s[3]), Resource::getCreatedTime);
            } else if (s[2].equals("like")) {
                objectLambdaQueryWrapper.orderBy(true, Boolean.parseBoolean(s[3]), Resource::getUlike);
            }
            else if (s[2].equals("see")) {
                objectLambdaQueryWrapper.orderBy(true, Boolean.parseBoolean(s[3]), Resource::getSee).eq(Resource::getResourceClass, s[1]);
            }else {
                objectLambdaQueryWrapper = null;
            }

        }

        return resourceMapper.selectPage(ipage, objectLambdaQueryWrapper);
    }

    public int UpdateResource(Resource resource) {
        resource.setModifyTime(LocalDateTime.now());
        return resourceMapper.update(resource, null);
    }

    public List<Resource> FindByUser(Long id) {


        return resourceMapper.selectList(new QueryWrapper<Resource>().eq("createUserId", String.valueOf(id)));

    }

    @Transactional
    public List<Resource> FindByType(String id) {
        return resourceMapper.selectList(new QueryWrapper<Resource>().eq("resourceClass", id));
    }

    //收藏资源 获取当前用户以及资源的id 添加到收藏表
    public int collectResource(Resource resource) {
        Collect collect = new Collect();
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

    //获取这个数组 被点赞用户id 点赞id 被点赞对象的id 对象类型
    public boolean upResource(String[] resource) {
        //之后再弄细分
        userClient.scoreChange(resource[0]+"::"+"1");
        return redisService.saveLiked2Redis(resource[0], resource[1],resource[2],resource[3] );
    }

    public List<Resource> SearchResource(String msg) {
        QueryWrapper<Resource> resourceQueryWrapper = new QueryWrapper<>();
        resourceQueryWrapper.like("title",msg).or().like("createUser",msg);
        return resourceMapper.selectList(resourceQueryWrapper);


    }

    public boolean downResource(String[] msgs) {
        return redisService.unlikeFromRedis(msgs[0],msgs[1],msgs[2],msgs[3]);
    }
}
