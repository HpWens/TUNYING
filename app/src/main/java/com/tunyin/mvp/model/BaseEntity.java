package com.tunyin.mvp.model;

import android.text.TextUtils;

import com.tunyin.constants.ReturnCode;

import java.io.Serializable;

public class BaseEntity<T> implements Serializable {
    public String desc;
    public String code;
    public T content;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }


    public boolean isSuccess() {
        return TextUtils.equals(code, ReturnCode.CODE_SUCCESS);
    }

}

