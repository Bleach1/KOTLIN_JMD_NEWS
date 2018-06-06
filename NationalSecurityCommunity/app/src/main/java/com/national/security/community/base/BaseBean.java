package com.national.security.community.base;

/**
 * @ description:基础Bean
 * @ author: ljn
 * @ time:  2017/12/7
 */
public class BaseBean<T> {

    private String msg;
    private int code;
    private T data;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //判断数据是否相同
    private String id;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
