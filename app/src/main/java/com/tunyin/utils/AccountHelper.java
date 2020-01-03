package com.tunyin.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.tunyin.constants.KeyConstants;
import com.tunyin.mvp.model.UserInfoEntity;

/**
 * Created by yiang on 2018/4/9.
 */

public class AccountHelper {

    // 未认证
    public final static int AUTH_NULL = 0;
    // 认证中
    public final static int AUTH_ING = 1;
    // 用户认证通过
    public final static int AUTH_COMPLETE = 2;
    // 认证拒绝
    public final static int AUTH_REJECT = 3;

    public static void clear() {
        DataCacheUtil.getInstance().clean();
    }

    public static void saveUserInfo(UserInfoEntity userInfoEntity) {
        DataCacheUtil.getInstance().setStringValue(KeyConstants.KEY_USER_INFO, new Gson().toJson(userInfoEntity));
    }

    public static UserInfoEntity getUserInfo() {
        String object = DataCacheUtil.getInstance().getStringValue(KeyConstants.KEY_USER_INFO);
        UserInfoEntity userInfoEntity = new Gson().fromJson(object, UserInfoEntity.class);
        if (userInfoEntity == null) userInfoEntity = new UserInfoEntity();
        return userInfoEntity;
    }


    /**
     * 是否上户
     *
     * @return
     */
    public static boolean isBusy() {
        return TextUtils.equals(getUserInfo().getIsBusy(), "1");
    }

    /**
     * 获取账户状态
     *
     * @return 0:禁用 1:已认证 2:未认证 3:认证中 4:认证失败
     */
    public static int getStatus() {
        return getUserInfo().getStatus();
    }

    /**
     * 获取认证状态
     *
     * @return 0:未认证 1:认证中 2:已认证 3:认证失败
     */
    public static int getAuthStatus() {
        return getUserInfo().getMatronStatus();
    }

    /**
     * 设置认证状态
     *
     * @return
     */
    public static void setAuthStatus(int status) {
        UserInfoEntity userInfo = getUserInfo();
        userInfo.setMatronStatus(status);
        saveUserInfo(userInfo);
    }

    /**
     * 设置名片完善状态
     *
     * @param cardStatus
     */
    public static void setCardStatus(int cardStatus) {
        DataCacheUtil.getInstance().setIntVale("cardStatus", cardStatus);
    }

    /**
     * @return
     */
    public static int getCardStatus() {
        return DataCacheUtil.getInstance().getIntVale("cardStatus", 0);
    }

    /**
     * 未认证或者认证被拒绝
     */
    public static boolean isAuthNull() {
        int matronStatus = getUserInfo().getMatronStatus();
        return matronStatus != 1 && matronStatus != 2;
    }

    /**
     * 是否已经登录过
     *
     * @return
     */
    public static boolean hasLogin() {
        String phone = getPhone();
//        if (TextUtils.isEmpty(phone)) {
//            return false;
//        }
        return !TextUtils.isEmpty(getToken());
    }

    /**
     * 保存登录信息
     */
//    public static void saveLoginResponse(LoginEntity loginEntity) {
//        DataCacheUtil.getInstance().setStringValue(KeyConstants.KEY_LOGIN_DATA, new Gson().toJson(loginEntity));
//    }
//
//    public static LoginEntity getLoginResponse() {
//        String object = DataCacheUtil.getInstance().getStringValue(KeyConstants.KEY_LOGIN_DATA);
//        LoginEntity entity = new Gson().fromJson(object, LoginEntity.class);
//        return entity;
//    }

    /**
     * 保存是否有密码
     */
    public static void setWalletPasswordExists(Boolean exists) {
        DataCacheUtil.getInstance().setBooleanValue(KeyConstants.KEY_WALLET_PWD_EXISTS, exists);
    }

    /**
     * 保存是否有密码
     */
    public static boolean isWalletPasswordExists() {
        return DataCacheUtil.getInstance().getBooleanValue(KeyConstants.KEY_WALLET_PWD_EXISTS, false);
    }

    /**
     * 保存登录的手机号
     */
    public static void savePhone(String phone) {
        DataCacheUtil.getInstance().setStringValue(KeyConstants.KEY_PHONE, phone);
    }

    /**
     * 保存月嫂等级
     */
    public static void saveMatronType(String type) {
        DataCacheUtil.getInstance().setStringValue(KeyConstants.KEY_MATRON_TYPE, type);
    }

    /**
     * 获取月嫂等级
     *
     * @return 2 金嫂    1 靓嫂    0 月嫂
     */
    public static String getMatronType() {
        return DataCacheUtil.getInstance().getStringValue(KeyConstants.KEY_MATRON_TYPE);
    }

    /**
     * 获取登录的手机
     *
     * @return
     */
    public static String getPhone() {
        return DataCacheUtil.getInstance().getStringValue(KeyConstants.KEY_PHONE);
    }

    /**
     * 保存token信息
     */
    public static void saveToken(String token) {
        DataCacheUtil.getInstance().setStringValue(KeyConstants.KEY_TOKEN, token);
    }


    /**
     * 获取token信息
     */
    public static String getToken() {
        return DataCacheUtil.getInstance().getStringValue(KeyConstants.KEY_TOKEN);
    }

    /**
     * 保存token信息
     */
    public static void saveUpToken(String token) {
        DataCacheUtil.getInstance().setStringValue(KeyConstants.KEY_UP_TOKEN, token);
    }

    /**
     * 获取token信息
     */
    public static String getUpToken() {
        return DataCacheUtil.getInstance().getStringValue(KeyConstants.KEY_UP_TOKEN);
    }

    /**
     * 是否设置了密码
     *
     * @return
     */
    public static boolean hasSetPassword() {
        return !TextUtils.isEmpty(getUserInfo().getPassword());
    }

    /**
     * 是否设置了支付密码
     *
     * @return
     */
    public static boolean hasSetPayPassword() {
        return !TextUtils.isEmpty(getUserInfo().getPayPassword());
    }


    /**
     * 是否第一次进入app
     *
     * @return
     */
    public static boolean isFirstEnterApp() {
        boolean isFirst = DataCacheUtil.getInstance().getBooleanValue(KeyConstants.KEY_FIRST_ENTER, true);
//        LogUtil.d(isFirst + "");
        return isFirst;
    }

    /**
     * 设置标志，已经进入过app
     */
    public static void setHasEnterApp() {
        DataCacheUtil.getInstance().setBooleanValue(KeyConstants.KEY_FIRST_ENTER, false);
    }

    /**
     * 设置标志，集字是否已经弹框
     */
    public static void setShowCashWordDialog() {
        DataCacheUtil.getInstance().setBooleanValue(KeyConstants.ACTION_SHOW_CASHDIALOG, true);
    }

    /**
     * 是否已经弹框过兑奖弹框
     */
    public static boolean isShowCashWordDialog() {
        boolean isShow = DataCacheUtil.getInstance().getBooleanValue(KeyConstants.ACTION_SHOW_CASHDIALOG, false);
        return isShow;
    }

    /**
     * 退出登录
     */
    public static void exitAccount() {
        DataCacheUtil.getInstance().setStringValue(KeyConstants.KEY_USER_INFO, "");
        DataCacheUtil.getInstance().setStringValue(KeyConstants.KEY_TOKEN, "");
//        ActivityManagerUtil.getInstance().removeOneActivity(SettingActivity.this);
//        clear();
        ActivityManagerUtil.getInstance().finishAllActivity();
    }

    public static void clearLoginState() {
        DataCacheUtil.getInstance().setStringValue(KeyConstants.KEY_TOKEN, "");
    }

    /**
     * 用户协议数据
     *
     * @param agreeEntity
     */
//    public static void saveAgree(AgreeEntity agreeEntity) {
//        DataCacheUtil.getInstance().setStringValue(KeyConstants.KEY_AGREE, new Gson().toJson(agreeEntity));
//    }
//
//
//    public static String getAgree() {
//        String str = DataCacheUtil.getInstance().getStringValue(KeyConstants.KEY_AGREE);
//        AgreeEntity entity = new Gson().fromJson(str, AgreeEntity.class);
//        if (entity == null) return "";
//        return entity.getContent();
//    }

    /**
     * 是否第一次进入推荐人界面
     *
     * @return
     */
    public static boolean isFirstEnteReferrer() {
        boolean isFirst = DataCacheUtil.getInstance().getBooleanValue(KeyConstants.KEY_FIRST_ENTER_REFERRER, true);
//        LogUtil.d(isFirst + "");
        return isFirst;
    }

    /**
     * 设置标志，已经进入过推荐人界面
     */
    public static void setFitstEnterReferrer(boolean isEnter) {
        DataCacheUtil.getInstance().setBooleanValue(KeyConstants.KEY_FIRST_ENTER_REFERRER, isEnter);
    }


    /**
     * 判断用户信息是否完善
     *
     * @return
     */
    public static boolean isCompleteInfo() {
        UserInfoEntity entity = getUserInfo();
        if (entity == null) return false;

        if (TextUtils.isEmpty(entity.getName())) {
            return false;
        }
//        else if (TextUtils.isEmpty(entity.getPhone())) {
//            return false;
//        }
//        else if (TextUtils.isEmpty(entity.getPhone())) {
//            return false;
//        }
//        else if (TextUtils.isEmpty(entity.getIdcard())) {
//            return false;
//        }
        else if (TextUtils.isEmpty(entity.getBirthdate())) {
            return false;
        }
//        else if (TextUtils.isEmpty(entity.getPlaceId())) {
//            return false;
//        }
//        else if (TextUtils.isEmpty(entity.getCityId())) {
//            return false;
//        }
        else if (TextUtils.isEmpty(entity.getPlaceId())) {
            return false;
        } else if (entity.getTakecareBabies() > 0) {
            return false;
        }
//        else if (TextUtils.isEmpty(entity.getNation())) {
//            return false;
//        }
        return true;
    }

    public static void saveRegisterId(String register) {
        DataCacheUtil.getInstance().setStringValue(KeyConstants.KEY_REGISTERID, register);
    }

    public static String getRegisterId() {
        return DataCacheUtil.getInstance().getStringValue(KeyConstants.KEY_REGISTERID);
    }
}
