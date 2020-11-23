package com.tunyin.ui.activity.mine;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.meis.base.mei.base.BaseActivity;
import com.tunyin.R;
import com.tunyin.ui.adapter.mine.MsgVPAdapter;
import com.tunyin.utils.eye.Eyes;

public class MyMsgActivity2 extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mTvTitle;

    private SlidingTabLayout mTabLayout;
    private ViewPager mViewPager;

    private final String[] mTabTitles = {
            "系统通知"
    };

    @Override
    protected void initView() {
        Eyes.setStatusBarColor(this, Color.parseColor("#FFFFFF"));

        mToolbar = findViewById(R.id.toolbar);
        mTvTitle = findViewById(R.id.tv_title);
        findViewById(R.id.tv_right_title).setVisibility(View.GONE);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(v -> finish());
        mTvTitle.setText("我的消息");

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_page);

        mViewPager.setAdapter(new MsgVPAdapter(getSupportFragmentManager(), mTabTitles));

        mTabLayout.setViewPager(mViewPager);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int layoutResId() {
        return R.layout.activity_my_msg2;
    }
}
