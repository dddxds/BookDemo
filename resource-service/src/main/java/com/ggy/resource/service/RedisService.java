package com.ggy.resource.service;

import com.ggy.pojo.LikedCountDTO;
import com.ggy.pojo.LikedStatusEnum;
import com.ggy.pojo.UserLike;
import com.ggy.resource.util.RedisKeyUtils;
import com.ggy.resource.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 *  @title: RedisService
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-03-30 8:44
 ***/
@Service
@Slf4j
public class RedisService {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    LikedService likedService;

    /**
     * 点赞。状态为1
     * @param likedUserId
     * @param likedPostId
     */
    public void saveLiked2Redis(String likedUserId, String likedPostId) {
        String key = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
        redisUtil.hset(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.LIKE.getCode());

    }
    /**
     * 取消点赞。将状态改变为0
     * @param likedUserId
     * @param likedPostId
     */
    public void unlikeFromRedis(String likedUserId, String likedPostId) {
        String key = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
        redisUtil.hset(RedisKeyUtils.MAP_KEY_USER_LIKED, key, LikedStatusEnum.UNLIKE.getCode());

    }
    /**
     * 从Redis中删除一条点赞数据
     * @param likedUserId
     * @param likedPostId
     */

    public void deleteLikedFromRedis(String likedUserId, String likedPostId) {
        String key = RedisKeyUtils.getLikedKey(likedUserId, likedPostId);
        redisUtil.hdel(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
    }
    /**
     * 该用户的点赞数加1
     * @param likedUserId
     */
    public void incrementLikedCount(String likedUserId) {
        redisUtil.hincr(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, likedUserId, 1);

    }
    /**
     * 该用户的点赞数减1
     * @param likedUserId
     */
    public void decrementLikedCount(String likedUserId) {
        redisUtil.hdecr(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, likedUserId, 1);

    }
    /**
     * 获取Redis中存储的所有点赞数据
     * @return
     */
    public List<UserLike> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisUtil.hscan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<UserLike> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedUserId,likedPostId
            String[] split = key.split("::");
            String likedUserId = split[0];
            String likedPostId = split[1];
            Integer value = (Integer) entry.getValue();

            //组装成 UserLike 对象
            UserLike userLike = new UserLike(likedUserId, likedPostId, value);
            list.add(userLike);

            //存到 list 后从 Redis 中删除
            redisUtil.hdel(RedisKeyUtils.MAP_KEY_USER_LIKED, key);

        }

        return list;
    }
/**
 * 获取Redis中存储的所有点赞数量
 * @return
 */
    public List<LikedCountDTO> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisUtil.hscan(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, ScanOptions.NONE);
        List<LikedCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 LikedCountDT
            String key = (String)map.getKey();
            LikedCountDTO dto = new LikedCountDTO(key, (Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
            redisUtil.hdel(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, key);
        }
        return list;
    }


}
