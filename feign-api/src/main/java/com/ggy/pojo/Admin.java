package com.ggy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 *  @title: Admin
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023-04-17 14:13
 ***/
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_admin")
public class Admin extends BaseEntity {
    private String uName;
    private String uNumber;
    private String uPwd;
    private String picture;
    private Long upload;
    private Long level;

}