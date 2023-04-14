package com.ggy.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_resource")
public class Resource extends BaseEntity{
    private String content;
    private String picture;
    @TableField("resourceType")
    private String resourceType;
    private String title;
    @TableField("createUserId")
    private Long createUserId;
    @TableField("resourceClass")
    private String resourceClass;
    private String introduce;
    private Long ulike;
    private Integer see;

}
