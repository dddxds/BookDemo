package com.ggy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_comments")
public class Comment extends BaseEntity{
    private String content;
    private Long resourceId;
    private Long receiverId;
    private Long ulike;
}
