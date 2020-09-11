package com.meis.base.mei;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.meis.base.mei.entity.UserInfoEntity;
import com.meis.base.mei.service.TTAdManagerHolder;
import com.meis.base.mei.service.UserService;
import com.vondear.rxtool.RxTool;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;
import com.zhouyou.http.model.HttpHeaders;
import com.zhouyou.http.model.HttpParams;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApplication extends Application {

    // http://175.24.21.236:3000/mock/25/login/sendMsg
    private static final String BASE_URL = "http://175.24.21.236:9090";

    // 5051922 5062948
    public static final String TTADSDK_ID = "5051922";

    public static boolean isDebug = true;

    private static BaseApplication context;

    public static Context getInstance() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        isDebug = isDebug();

        // initARouter();

        initRxTool();

        initService();

        initEasyHttp();

        // initTTAdSdk();
    }

    // 初始化广告穿山甲
    private void initTTAdSdk() {
        // 初始化
        TTAdManagerHolder.init(this);
    }

    private void initService() {
        UserService.init(this);
    }

    /**
     * init Easy Http
     */
    private void initEasyHttp() {
        // 初始化网络框架
        EasyHttp.init(this);
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();

        UserInfoEntity userInfo = UserService.getInstance().getUserInfo();
        if (null != userInfo && !TextUtils.isEmpty(userInfo.token)) {
            headers.put("Authorization", userInfo.token);
        }
        headers.put("Content-Type", "application/json");

        // 设置请求参数
        HttpParams params = new HttpParams();
        EasyHttp.getInstance()
                .debug("RxEasyHttp", isDebug() ? true : false)
                .setReadTimeOut(10 * 60 * 1000)
                .setWriteTimeOut(10 * 60 * 1000)
                .setConnectTimeout(10 * 60 * 1000)
                .setRetryCount(3) // 默认网络不好自动重试3次
                .setRetryDelay(500) // 每次延时500ms重试
                .setRetryIncreaseDelay(500) // 每次延时叠加500ms
                .setBaseUrl(BASE_URL)
                .setCacheDiskConverter(new SerializableDiskConverter()) // 默认缓存使用序列化转化
                .setCacheMaxSize(50 * 1024 * 1024) // 设置缓存大小为50M
                .setCacheVersion(1) // 缓存版本为1
                .setCertificates() // 信任所有证书
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCommonHeaders(headers) // 设置全局公共头
                .addCommonParams(params); // 设置全局公共参数
    }

    private void initRxTool() {
        RxTool.init(this);
    }

    // init ARouter
//    private void initARouter() {
//        if (isDebug()) {
//            ARouter.openLog();
//            // Print log
//            ARouter.openDebug();
//        }
//        ARouter.init(this);
//    }

    public boolean isDebug() {
        boolean debuggable = false;
        PackageManager pm = getPackageManager();
        try {
            ApplicationInfo appInfo = pm.getApplicationInfo(getPackageName(), 0);
            debuggable = (0 != (appInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
        } catch (PackageManager.NameNotFoundException e) {
            /*debuggable variable will remain false*/
        }
        return debuggable;
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加头部
     */
    public static void addHttpTokenHeader() {
        HttpHeaders headers = new HttpHeaders();
        UserInfoEntity userInfo = UserService.getInstance().getUserInfo();
        if (null != userInfo && !TextUtils.isEmpty(userInfo.token)) {
            headers.put("Authorization", userInfo.token);
            headers.put("Content-Type", "application/json");
            EasyHttp.getInstance().getCommonHeaders().clear();
            EasyHttp.getInstance().addCommonHeaders(headers);
        }
    }

    public static void clearTokenHeader() {
        EasyHttp.getInstance().getCommonHeaders().clear();
    }

}
