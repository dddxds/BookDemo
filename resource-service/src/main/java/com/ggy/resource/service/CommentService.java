package com.ggy.resource.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ggy.fegin.UserClient;
import com.ggy.pojo.Comment;
import com.ggy.pojo.User;
import com.ggy.resource.mapper.CommentMapper;
import com.ggy.resource.mapper.ResourceMapper;
import com.ggy.resource.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/***
 *  @title: CommentService
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-03-29 19:09
 ***/
@Service
public class CommentService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserClient userClient;
    @Autowired
    private RedisUtil redisUtil;

    public HashMap<Object,Object> getComments(Long id) {
        List<Comment> comments = commentMapper.selectList(new QueryWrapper<Comment>().eq("resourceId", id));

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        comments.forEach(comment -> {
            objectObjectHashMap.put(userClient.findById(comment.getReceiverId()).getData(),comment);
        });

    // return commentMapper.selectList(new QueryWrapper<Comment>().eq("resourceId",String.valueOf(id)));
       return objectObjectHashMap;
    }

    public int addComment(Comment comment) {
        return commentMapper.insert(comment);
    }

    public int upComment(Comment comment) {
        long incr = redisUtil.incr(String.valueOf(comment.getId()), 1);
        return Math.toIntExact(incr);
    }
}
