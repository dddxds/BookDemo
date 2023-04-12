package com.ggy.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_userresponse")
public class UserResponse extends BaseEntity{
    private String content;
    private String atName;
    private long receiverId;
    private long commentId;
    private long responseType;
    private Long ulike;

}
