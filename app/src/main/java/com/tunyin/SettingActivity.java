package com.tunyin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tunyin.mvp.model.SelfBean;
import com.tunyin.ui.activity.mine.LoginActivity;
import com.tunyin.ui.dialog.CacheDialog;
import com.tunyin.ui.dialog.VersionDialog;
import com.tunyin.utils.eye.Eyes;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Eyes.setStatusBarColor(this, Color.parseColor("#FFFFFF"), true);
        setContentView(R.layout.activity_setting);

        findViewById(R.id.iv_back).setOnClickListener(v -> {
            finish();
        });

        findViewById(R.id.rl_person).setOnClickListener(v -> {

        });


        findViewById(R.id.iv_message).setOnClickListener(v -> {

        });


        findViewById(R.id.fl_cache).setOnClickListener(v -> {
            CacheDialog cacheDialog = new CacheDialog(SettingActivity.this, (CacheDialog.OnClickListener) (view, dialog) -> {

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
}
