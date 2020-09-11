package com.meis.base.mei.entity;

import java.io.Serializable;

/**
 * author: ws4
 * created on: 2018/4/11 14:34
 * description:下个版本会进行扩展
 */
public class Result<T> implements Serializable {

    public int code;
    public String msg = "";
    public T data;

    public boolean success;
    public int status = -1;

    public Result() {
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk() {
        return status == 0 && success;
    }
}
