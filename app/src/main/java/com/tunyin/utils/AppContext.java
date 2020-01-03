package com.tunyin.utils;

import android.content.Context;

public class AppContext {
    private static Context context;

    private AppContext() {
    }

    private static class SingletonHolder {
        private static final AppContext INSTANCE = new AppContext();
    }

    public static AppContext getInstance() {
        return AppContext.SingletonHolder.INSTANCE;
    }

    /**
     * 需在application中初始化
     * @param context
     */
    public synchronized void init(Context context) {
        AppContext.context = context;
    }

    public static Context getContext() {
        return context;
    }
}
