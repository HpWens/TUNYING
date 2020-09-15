package com.tunyin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.tunyin.base.BaseInjectActivity;
import com.tunyin.mvp.contract.mine.SettingContract;
import com.tunyin.mvp.model.SelfBean;
import com.tunyin.mvp.model.UserBean;
import com.tunyin.mvp.model.mine.VersionEntity;
import com.tunyin.mvp.presenter.mine.SettingPresenter;
import com.tunyin.ui.activity.mine.LoginActivity;
import com.tunyin.ui.dialog.CacheDialog;
import com.tunyin.ui.dialog.VersionDialog;
import com.tunyin.utils.DataCleanManager;
import com.tunyin.utils.eye.Eyes;
import com.tunyin.widget.dialog.ExitDialog;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class SettingActivity extends BaseInjectActivity<SettingPresenter> implements SettingContract.View {

    TextView tvCacheSize;

    ImageView ivMessage;

    private static final String MESSAGE_NOTIFICATION = "message_notification";

    private boolean isOpenMsg = true;


    @Override
    public void initInject() {
        super.initInject();
        getActivityComponent().inject(this);
    }

    @Override
    public void initPresenter() {
        super.initPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ivMessage = findViewById(R.id.iv_message);

        tvCacheSize = findViewById(R.id.tv_cache_size);

        isOpenMsg = SelfBean.getInstance().getMessageNotice().equals("1");

        ivMessage.setImageResource(isOpenMsg ? R.mipmap.setting_msg_open : R.mipmap.setting_msg_close);

        try {
            tvCacheSize.setText(DataCleanManager.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }

        findViewById(R.id.iv_back).setOnClickListener(v -> {
            finish();
        });

        findViewById(R.id.rl_person).setOnClickListener(v -> {
            startActivity(new Intent(v.getContext(), PersonalActivity.class));
        });


        ivMessage.setOnClickListener(v -> {
            if (isOpenMsg) {
                String hint = "此设置会导致全天都处于免打扰模式，不会再收到推送消息，是否继续？";
                CacheDialog cacheDialog = new CacheDialog(SettingActivity.this, (view, dialog) -> {
                    switchMessageMode();
                    dialog.dismiss();
                }, hint);
                cacheDialog.show();
                return;
            }
            switchMessageMode();
        });


        findViewById(R.id.fl_cache).setOnClickListener(v -> {
            CacheDialog cacheDialog = new CacheDialog(SettingActivity.this, (view, dialog) -> {
                DataCleanManager.clearAllCache(v.getContext());
                tvCacheSize.setText("0MB");
                dialog.dismiss();
            });
            cacheDialog.show();
        });

        findViewById(R.id.rl_update).setOnClickListener(v -> {
            mPresenter.getVersionInfo();
        });

        findViewById(R.id.fl_feed).setOnClickListener(v -> {
            startActivity(new Intent(this, FeedbackActivity.class));
        });

        findViewById(R.id.tv_exit).setOnClickListener(v -> new ExitDialog(this, isExit -> {
            if (isExit) {
                SelfBean.getInstance().setToken("");
                Intent intent = LoginActivity.newInstance(SettingActivity.this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }).show());

        mPresenter.getUserInfo();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Eyes.setStatusBarColor(this, Color.parseColor("#FFFFFF"), true);
    }

    private void switchMessageMode() {
        isOpenMsg = !isOpenMsg;
        // PreferenceUtils.INSTANCE.put(MESSAGE_NOTIFICATION, isOpenMsg);
        SelfBean.getInstance().setMessageNotice(isOpenMsg ? "1" : "0");
        ivMessage.setImageResource(isOpenMsg ? R.mipmap.setting_msg_open : R.mipmap.setting_msg_close);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void fillingUserInfo(@NotNull UserBean user) {
        if (user != null) {
            SelfBean.getInstance().setMessageNotice(TextUtils.isEmpty(user.messageNotice) ? "1" : user.messageNotice);

            if (SelfBean.getInstance().getMessageNotice().equals("1")) {
                isOpenMsg = true;
            } else {
                isOpenMsg = false;
            }
            ivMessage.setImageResource(isOpenMsg ? R.mipmap.setting_msg_open : R.mipmap.setting_msg_close);

            if (!TextUtils.isEmpty(user.nickName)) {
                SelfBean.getInstance().setNickName(user.nickName);
            }

            SelfBean.getInstance().setSex(TextUtils.isEmpty(user.sex) ? "未填写" : user.sex);

            if (!TextUtils.isEmpty(user.headUrl)) {
                SelfBean.getInstance().setHeadUrl(user.headUrl);
            }

            SelfBean.getInstance().setBirthday(TextUtils.isEmpty(user.birthday) ? "未填写" : user.birthday);

        }
    }

    /**
     * 获取版本号
     *
     * @param context 上下文
     * @return 版本号
     */
    public String getVersionCode(Context context) {

        //获取包管理器
        PackageManager pm = context.getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            //返回版本号
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "1.0";

    }

    @Override
    public void checkUpdate(@NotNull VersionEntity version) {
        if (version.isForce || version.newVersion) {
            VersionDialog versionDialog = new VersionDialog(SettingActivity.this, (view, dialog) -> {
                view.setEnabled(false);
                ((App) getApplication()).addEasyTokenHeader();
                EasyHttp.downLoad(version.downloadAddress)
                        .saveName(new Date().getTime() + ".apk")
                        .execute(new DownloadProgressCallBack<String>() {
                            @Override
                            public void onStart() {
                            }

                            @Override
                            public void onError(ApiException e) {
                            }

                            @Override
                            public void update(long bytesRead, long contentLength, boolean done) {
                                ToastUtils.INSTANCE.showToast("下载进度" + bytesRead / contentLength * 100 + "%");
                            }

                            @Override
                            public void onComplete(String path) {
                                VersionDialog.installApp(view.getContext(), path);
                            }
                        });
            });
            versionDialog.show();
        } else {
            ToastUtils.INSTANCE.showToast("已是最新版本");
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        String nick = SelfBean.getInstance().getNickName();
        if (nick.equals("未填写")) {
            nick = "";
        }

        String sex = SelfBean.getInstance().getSex();
        if (sex.equals("未填写")) {
            sex = "";
        } else {
            sex = sex.equals("男") ? "1" : "2";
        }

        String birthday = SelfBean.getInstance().getBirthday();
        if (birthday.equals("未填写")) {
            birthday = "";
        }

        mPresenter.updateUserInfo(SelfBean.getInstance().getHeadUrl(),
                nick,
                sex,
                birthday,
                SelfBean.getInstance().getMessageNotice());
    }

    @Override
    public void updateUserInfoSuc(@NotNull String response) {
        if (!TextUtils.isEmpty(response)) {
            // ToastUtils.INSTANCE.showToast("已更新用户数据");
        }
        finish();
    }
}
