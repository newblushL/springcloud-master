package com.springcloud.zuul;

/**
 * @description: 统一返回结果定义
 * @author: ll
 * @create: 2019-08-14 00:43
 */
public class ResponseData {
    private int code = 200;
    private String message;
    private Object data;

    public ResponseData(){
        super();
    }

    public ResponseData(int code,String message){
        this.code = code;
        this.message = message;
    }

    public static ResponseData fail(int code,String message){
        return new ResponseData(code,message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}