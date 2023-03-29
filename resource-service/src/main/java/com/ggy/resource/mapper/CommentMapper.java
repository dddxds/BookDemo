package com.ggy.resource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ggy.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

/***
 *  @title: CommentMapper
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-03-29 19:08
 ***/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
