package com.ggy.pojo;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;
    @TableField("createUser")
    private String createdUser; //创建人
    @TableField("createTime")
    private LocalDateTime createdTime ;//创建时间
    @TableField("modifyUser")
    private String modifyUser; //最后修改执行人
    @TableField("modifyTime")
    private LocalDateTime  modifyTime; //最后修改时间
    private Long status;
}
