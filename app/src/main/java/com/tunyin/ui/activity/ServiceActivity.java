package com.tunyin.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.tunyin.R;
import com.tunyin.base.BaseActivity;
import com.tunyin.utils.eye.Eyes;

import org.jetbrains.annotations.Nullable;

public class ServiceActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_service;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Eyes.translucentStatusBar(this, true);

        findViewById(R.id.iv_back).setOnClickListener(v -> {
            finish();
        });
    }
}
