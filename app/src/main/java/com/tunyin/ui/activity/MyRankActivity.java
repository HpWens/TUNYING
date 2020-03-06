package com.tunyin.ui.activity;

import com.tunyin.R;
import com.tunyin.base.BaseActivity;
import com.tunyin.utils.eye.Eyes;

public class MyRankActivity extends BaseActivity {

    @Override
    public void initWidget() {
        super.initWidget();
        Eyes.translucentStatusBar(this, true);
    }

    @Override
    public void loadData() {
        super.loadData();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_rank;
    }
}
