package com.ggy.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/***
 *  @title: LikedCountDTO
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-03-30 19:19
 ***/
@Entity
@Data
public class LikedCountDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    String key;
    Integer count;
    public LikedCountDTO (String key,  Integer count){
        this.key=key;
        this.count=count;

    }
}
