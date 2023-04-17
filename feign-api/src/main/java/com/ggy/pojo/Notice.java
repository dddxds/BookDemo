package com.ggy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 *  @title: Notice
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-04-17 14:08
 ***/
@TableName("t_notice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends BaseEntity{
    private String content;
}
