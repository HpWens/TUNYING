package com.tunyin.utils;

import com.zhouyou.http.EasyHttp;

import io.reactivex.Observable;

public class HttpUtils {
    private HttpUtils() {
    }

    private static class SingleTonHolder {
        private static HttpUtils INSTANCE = new HttpUtils();
    }

    public static HttpUtils getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    // 交易记录
    public Observable<String> getTransactionRecordList(int page, int type) {
        return EasyHttp.get("api/user/transactionRecord")
                .params("type", type + "")
                .params("offset", (page * 20) + "")
                .params("limit", "20")
                .execute(String.class);
    }

    // 我的消息
    public Observable<String> getMyMsgList(int page, int type) {
        return EasyHttp.get("api/user/transactionRecord")
                .params("type", type + "")
                .params("offset", (page * 20) + "")
                .params("limit", "20")
                .execute(String.class);
    }
}