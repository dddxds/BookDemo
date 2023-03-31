package com.ggy.resource.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ggy.pojo.UserLike;
import com.ggy.resource.mapper.UserLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 *  @title: UserLikeRepository
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-03-30 20:12
 ***/
@Service
public class UserLikeRepository {
    @Autowired
    private UserLikeMapper userLikeMapper;
    public boolean save(UserLike userLike) {
        int i=userLikeMapper.insert(userLike);
        if(i!=0){
            return true;
        }else {
            return false;
        }
    }

    public boolean saveAll(List<UserLike> list) {
        int i=0;
        for (UserLike ul:list) {
            i=userLikeMapper.insert(ul);
        }
        if(i!=0){
            return true;
        }else {
            return false;
        }

    }

    public IPage<UserLike> findByLikedUserIdAndStatus(String likedUserId, Integer code, IPage page) {
        QueryWrapper<UserLike> userLikeQueryWrapper = new QueryWrapper<>();

        userLikeQueryWrapper.eq("liked_user_id",likedUserId).eq("status",code);
        return userLikeMapper.selectPage(page,userLikeQueryWrapper);
    }

    public IPage<UserLike> findByLikedPostIdAndStatus(String likedPostId, Integer code, IPage pageable) {
        QueryWrapper<UserLike> userLikeQueryWrapper = new QueryWrapper<>();
        userLikeQueryWrapper.eq("liked_post_id",likedPostId).eq("status",code);
        return userLikeMapper.selectPage(pageable,userLikeQueryWrapper);
    }

    public UserLike findByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
        QueryWrapper<UserLike> userLikeQueryWrapper = new QueryWrapper<>();
        userLikeQueryWrapper.eq("liked_post_id",likedPostId).eq("liked_user_id",likedUserId);
        return userLikeMapper.selectOne(userLikeQueryWrapper);

    }
}
