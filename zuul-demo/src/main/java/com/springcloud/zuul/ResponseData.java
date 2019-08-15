package com.springcloud.zuul;

/**
 * @description: 统一返回结果定义
 * @author: ll
 * @create: 2019-08-14 00:43
 */
public class ResponseData {
    private Boolean status = true;
    private int code = 200;
    private String message;
    private Object data;

    public ResponseData(){
        super();
    }

    public ResponseData(String message, int code){
        this.message = message;
        this.code = code;
    }

    public static ResponseData fail(String message, int code){
        return new ResponseData(message,code);
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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