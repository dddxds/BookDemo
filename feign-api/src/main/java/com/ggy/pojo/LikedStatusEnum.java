package com.ggy.pojo;

import lombok.Getter;

/***
 *  @title: LikedStatusEnum
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-03-30 8:34
 ***/
@Getter
public enum LikedStatusEnum {
    LIKE(1, "点赞"),
    UNLIKE(0, "取消点赞/未点赞"),
    ;

    private Integer code;

    private String msg;

    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
