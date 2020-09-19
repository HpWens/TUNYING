package com.meis.base.mei.entity;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * author: ws4
 * created on: 2018/4/11 14:34
 * description:下个版本会进行扩展
 */
public class Result<T> implements Serializable {

    public String code;
    public String msg = "";
    public String desc;
    public T data;
    public T content;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean isOk() {
        return !TextUtils.isEmpty(code) && code.equals("200");
    }
}
