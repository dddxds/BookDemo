package com.ggy.config;

/***
 *  @title: Result
 *  @author: GaoGuiYun
 *  @version: 1.0.0
 *  @create: 2023/2/10 12:35
 ***/
public class Result {
    private Result(){

    }

    public static AjaxResult success(){
        AjaxResult<Object> result = new AjaxResult<>();
        result.setCode(HttpStatus.SUCCESS.getCode());
        result.setMsg(HttpStatus.SUCCESS.getValue());
        return result;
    }

    public static AjaxResult success(String msg){
        AjaxResult<Object> result = new AjaxResult<>();
        result.setCode(HttpStatus.SUCCESS.getCode());
        result.setMsg(msg);
        return result;
    }

    public static <T> AjaxResult success(T data){
        AjaxResult<Object> result = new AjaxResult<>();
        result.setCode(HttpStatus.SUCCESS.getCode());
        result.setMsg(HttpStatus.SUCCESS.getValue());
        result.setData(data);
        return result;
    }

    public static <T> AjaxResult success(int code, String msg, T data){
        AjaxResult<Object> result = new AjaxResult<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static AjaxResult error(){
        AjaxResult<Object> result = new AjaxResult<>();
        result.setCode(HttpStatus.ERROR.getCode());
        result.setMsg(HttpStatus.ERROR.getValue());
        return result;
    }

    public static AjaxResult error(String msg){
        AjaxResult<Object> result = new AjaxResult<>();
        result.setCode(HttpStatus.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }
}
