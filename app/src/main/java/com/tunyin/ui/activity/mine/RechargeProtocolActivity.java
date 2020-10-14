package com.tunyin.ui.activity.mine;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.meis.base.mei.base.BaseActivity;
import com.tunyin.R;
import com.tunyin.utils.eye.Eyes;

public class RechargeProtocolActivity extends BaseActivity {
    TextView tvTitle;
    private Toolbar mToolbar;

    @Override
    protected void initView() {
        Eyes.setStatusBarColor(this, Color.parseColor("#FFFFFF"), true);
        tvTitle = findViewById(R.id.tv_title);
        tvTitle.setText("充值协议");

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int layoutResId() {
        return R.layout.activity_recharge_protocol;
    }
}
