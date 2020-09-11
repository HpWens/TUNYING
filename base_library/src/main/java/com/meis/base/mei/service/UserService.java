package com.meis.base.mei.service;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.meis.base.mei.entity.UserInfoEntity;
import com.meis.base.mei.utils.ACache;
import com.meis.base.mei.utils.ParseJsonUtils;
import com.vondear.rxtool.RxSPTool;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private volatile static UserService singleton = null;

    private static Application mContext;

    private static final String USER_ENTRY = "USER_ENTRY";

    private static final String SEARCH_TAG_HISTORY = "search_tag_history";

    private UserService() {
    }

    public static UserService getInstance() {
        testInitialize();
        if (singleton == null) {
            synchronized (UserService.class) {
                if (singleton == null) {
                    singleton = new UserService();
                }
            }
        }
        return singleton;
    }

    private static void testInitialize() {
        if (mContext == null)
            throw new ExceptionInInitializerError("请先在全局 Application 中调用 UserService.init() 初始化！");
    }

    /**
     * @param app
     */
    public static void init(Application app) {
        mContext = app;
    }

    public void saveUser(UserInfoEntity user) {
        ACache.get(mContext).put(USER_ENTRY, user);
    }

    public void clearUser() {
        ACache.get(mContext).put(USER_ENTRY, new UserInfoEntity());
    }

    public UserInfoEntity getUserInfo() {
        Object userObj = ACache.get(mContext).getAsObject(USER_ENTRY);
        if (null != userObj && userObj instanceof UserInfoEntity) {
            return (UserInfoEntity) userObj;
        }
        return new UserInfoEntity();
    }

    public String getToken() {
        UserInfoEntity userInfo = getUserInfo();
        if (null != userInfo) {
            return userInfo.token;
        }
        return "";
    }

    public String getUserId() {
        UserInfoEntity userInfo = getUserInfo();
        if (null != userInfo) {
            return userInfo.userId;
        }
        return "";
    }

    public boolean isLogin() {
        if (TextUtils.isEmpty(getToken())) {
            return false;
        }
        return true;
    }


    // 全局搜索保存历史记录
    public void saveTagHistorySearch(String record) {
        ACache.get(mContext).put(SEARCH_TAG_HISTORY, record);
    }

    public List<String> getTagHistorySearch() {
        String data = ACache.get(mContext).getAsString(SEARCH_TAG_HISTORY);
        if (TextUtils.isEmpty(data)) {
            return new ArrayList<>();
        } else {
            return ParseJsonUtils.parseListData(data, String.class);
        }
    }

    public void addHistorySearch(String key) {
        if (TextUtils.isEmpty(key)) return;
        List<String> data = getTagHistorySearch();
        if (data != null) {
            if (data.isEmpty()) {
                data.add(key);
            } else {
                if (data.contains(key)) {
                    data.remove(key);
                }
                data.add(0, key);
            }
            saveTagHistorySearch(new Gson().toJson(data));
        }
    }

    public void clearTgaHistorySearch() {
        saveTagHistorySearch("");
    }

    private static final String USER_PHONE = "user_phone";

    public void saveUserPhone(Context context, String phone) {
        RxSPTool.putString(context, USER_PHONE, phone);
    }

    public String getUserPhone(Context context) {
        return RxSPTool.getString(context, USER_PHONE);
    }
}
