package com.ggy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/***
 *  @title: UserLike
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-03-30 9:31
 ***/
@Entity
@Data
@TableName("t_user_like")
public class UserLike {
    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //被点赞的用户的id
    @TableField("liked_user_id")
    private String likedUserId;

    //点赞的用户的id
    @TableField("liked_post_id")
    private String likedPostId;

    //点赞的状态.默认未点赞
    private Integer status = LikedStatusEnum.UNLIKE.getCode();

    public UserLike() {
    }

    public UserLike(String likedUserId, String likedPostId, Integer status) {
        this.likedUserId = likedUserId;
        this.likedPostId = likedPostId;
        this.status = status;
    }
}
