package com.ggy.resource.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ggy.fegin.UserClient;
import com.ggy.pojo.LikedCountDTO;
import com.ggy.pojo.LikedStatusEnum;
import com.ggy.pojo.User;
import com.ggy.pojo.UserLike;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 *  @title: LikedService
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-03-30 15:19
 ***/
@Service
@Slf4j
public class LikedService {
    @Autowired
    UserLikeRepository likeRepository;

    @Autowired
    RedisService redisService;

    @Autowired
    UserClient userClient;

    /**
     * 保存点赞记录
     * @param userLike
     * @return
     */

    @Transactional
    public boolean save(UserLike userLike) {
        return likeRepository.save(userLike);
    }

    /**
     * 批量保存或修改
     * @param list
     */

    @Transactional
    public boolean saveAll(List<UserLike> list) {
        return likeRepository.saveAll(list);
    }

    /**
     * 根据被点赞人的id查询点赞列表（即查询都谁给这个人点赞过）
     * @param likedUserId 被点赞人的id
     * @param pageable
     * @return
     */

    public IPage<UserLike> getLikedListByLikedUserId(String likedUserId, IPage pageable) {
        return likeRepository.findByLikedUserIdAndStatus(likedUserId, LikedStatusEnum.LIKE.getCode(), pageable);
    }

    /**
     * 根据点赞人的id查询点赞列表（即查询这个人都给谁点赞过）
     * @param likedPostId
     * @param page
     * @return
     */

    public IPage<UserLike> getLikedListByLikedPostId(String likedPostId, IPage page) {
        return likeRepository.findByLikedPostIdAndStatus(likedPostId, LikedStatusEnum.LIKE.getCode(), page);
    }

    /**
     * 通过被点赞人和点赞人id查询是否存在点赞记录
     * @param likedUserId
     * @param likedPostId
     * @return
     */

    public UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
        return likeRepository.findByLikedUserIdAndLikedPostId(likedUserId, likedPostId);
    }

    /**
     * 将Redis里的点赞数据存入数据库中
     */

   @Transactional
   public void transLikedFromRedis2DB() {
        List<UserLike> list = redisService.getLikedDataFromRedis();
        for (UserLike like : list) {
            UserLike ul = getByLikedUserIdAndLikedPostId(like.getLikedUserId(), like.getLikedPostId());
            if (ul == null){
                //没有记录，直接存入
                save(like);
            }else{
                //有记录，需要更新
                ul.setStatus(like.getStatus());
                save(ul);
            }
        }
    }

    /**
     * 将Redis中的点赞数量数据存入数据库
     */

    @Transactional
    public void transLikedCountFromRedis2DB() {
        List<LikedCountDTO> list = redisService.getLikedCountFromRedis();
        for (LikedCountDTO dto : list) {
            User user = (User) userClient.findById(Long.valueOf(dto.getId())).getData();
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (user != null){
            Long likeNum = user.getUlike() + dto.getCount();
            user.setUlike(likeNum);
            //更新点赞数量
            userClient.updateInfo(user);
        }
    }
}
}
