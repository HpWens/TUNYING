package com.tunyin.constants;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ReturnCode {
    /**
     * 服务器错误码
     */
    //成功
    public static final String CODE_SUCCESS = "100000";
    //失败
    public static final String CODE_ERROR = "-1";
    //token过期
    public static final String CODE_TOKEN_EXPIRE = "401";

    /**
     * 本地错误码
     */
    //未知错误
    public static final String CODE_LOCAL_UNKNOWN_ERROR = "0x10100";
    //无网络状态
    public static final String CODE_LOCAL_NO_NETWORK_ERROR = "0x10101";
    //连接超时
    public static final String CODE_LOCAL_TIMEOUT_ERROR = "0x10102";


    @StringDef({CODE_SUCCESS, CODE_ERROR, CODE_TOKEN_EXPIRE, CODE_LOCAL_UNKNOWN_ERROR,
            CODE_LOCAL_NO_NETWORK_ERROR, CODE_LOCAL_TIMEOUT_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Code {

    }
}

