package com.ggy.config;

import lombok.Data;

/***
 *  @title: AjaxResult
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023/2/10 12:34
 ***/
@Data
public class AjaxResult<T> {
    /**
     * 状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;
}
