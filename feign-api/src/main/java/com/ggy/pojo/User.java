package com.ggy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("t_user")
public class User extends BaseEntity {
    private String uName;
    private String uNumber;
    private String uPwd;
    private String email;
    private String picture;
    private Long ulike;
    private Long upload;

}
