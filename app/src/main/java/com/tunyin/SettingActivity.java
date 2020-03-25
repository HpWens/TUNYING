package com.tunyin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tunyin.mvp.model.SelfBean;
import com.tunyin.ui.activity.mine.LoginActivity;
import com.tunyin.ui.dialog.CacheDialog;
import com.tunyin.ui.dialog.VersionDialog;
import com.tunyin.utils.DataCleanManager;
import com.tunyin.utils.PreferenceUtils;
import com.tunyin.utils.eye.Eyes;

public class SettingActivity extends AppCompatActivity {

    TextView tvCacheSize;

    ImageView ivMessage;

    private static final String MESSAGE_NOTIFICATION = "message_notification";

    private boolean isOpenMsg = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Eyes.setStatusBarColor(this, Color.parseColor("#FFFFFF"), true);
        setContentView(R.layout.activity_setting);

        ivMessage = findViewById(R.id.iv_message);

        tvCacheSize = findViewById(R.id.tv_cache_size);

        isOpenMsg = (boolean) PreferenceUtils.INSTANCE.get(MESSAGE_NOTIFICATION, true);

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
            VersionDialog versionDialog = new VersionDialog(SettingActivity.this, (view, dialog) -> {

            });
            versionDialog.show();
        });

        findViewById(R.id.fl_feed).setOnClickListener(v -> {

        });

        findViewById(R.id.tv_exit).setOnClickListener(v -> {
            SelfBean.getInstance().setToken("");
            startActivity(LoginActivity.newInstance(this));
            finish();
        });


    }

    private void switchMessageMode() {
        isOpenMsg = !isOpenMsg;
        PreferenceUtils.INSTANCE.put(MESSAGE_NOTIFICATION, isOpenMsg);
        ivMessage.setImageResource(isOpenMsg ? R.mipmap.setting_msg_open : R.mipmap.setting_msg_close);
    }
}
