package com.tunyin.utils;

import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBack;

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
        return EasyHttp.post("api/user/transactionRecord")
                .params("type", type + "")
                .params("offset", ((page - 1) * 20) + "")
                .params("limit", "20")
                .execute(String.class);
    }

    // 我的消息
    public Observable<String> getMyMsgList(int page, int type) {
        return EasyHttp.post("api/message/myMessage")
                .params("type", type + "")
                .params("offset", ((page - 1) * 20) + "")
                .params("limit", "20")
                .execute(String.class);
    }

    // 微信登录
    public void wechatLogin(String openId, CallBack<String> callBack) {
        EasyHttp.post("api/user/thirdPartyLogin")
                .params("type", "wechat")
                .params("openid", openId)
                .execute(callBack);
    }
}
