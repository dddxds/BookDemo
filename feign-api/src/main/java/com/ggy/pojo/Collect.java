package com.ggy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/***
 *  @title: Collect
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-03-29 20:53
 ***/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_collect")
public class Collect {
    private Long id;
    private Long userId;
    private Long resourceId;
    private LocalDateTime collectDate;
}
