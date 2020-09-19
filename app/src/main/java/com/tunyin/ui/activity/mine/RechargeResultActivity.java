package com.tunyin.ui.activity.mine;


import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.meis.base.mei.base.BaseActivity;
import com.tunyin.R;
import com.tunyin.utils.eye.Eyes;

public class RechargeResultActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mTvTitle;

    private boolean isSuccess = true;

    private ImageView ivRecharge;
    private TextView tvRecharge;
    private TextView tvTry;

    @Override
    protected void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("is_success")) {
                isSuccess = intent.getBooleanExtra("is_success", true);
            }
        }
        Eyes.setStatusBarColor(this, Color.parseColor("#FFFFFF"));


        mToolbar = findViewById(R.id.toolbar);
        mTvTitle = findViewById(R.id.tv_title);
        findViewById(R.id.tv_right_title).setVisibility(View.GONE);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(v -> finish());
        mTvTitle.setText(isSuccess ? "充值成功" : "支付失败");

        ivRecharge = findViewById(R.id.iv_recharge);
        tvRecharge = findViewById(R.id.tv_recharge);
        tvTry = findViewById(R.id.tv_try);

        ivRecharge.setImageResource(isSuccess ? R.mipmap.recharge_success_icon : R.mipmap.recharge_failure_icon);
        tvRecharge.setText(isSuccess ? "恭喜充值成功！" : "抱歉， 未完成付款\n发生未知错误");
        tvRecharge.setTextColor(isSuccess ? Color.parseColor("#5e8cf8") : Color.parseColor("#999999"));
        tvTry.setVisibility(isSuccess ? View.GONE : View.VISIBLE);
        tvTry.setOnClickListener(v -> finish());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int layoutResId() {
        return R.layout.activity_recharge_result;
    }
}
