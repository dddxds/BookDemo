package com.ggy.config;

/***
 *  @title: HttpStatus
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023/2/10 12:31
 ***/
public enum HttpStatus {
    SUCCESS(200,"操作成功"),

    BAD_REQUEST(401,"参数列表错误"),

    NOT_FOUND(404,"资源，服务未找到"),

    ERROR(500, "操作失败");

    private Integer code;

    private String value;


    HttpStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode(){
        return this.code;
    }

    public String getValue(){
        return this.value;
    }
}
