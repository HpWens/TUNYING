package com.tunyin;

import android.graphics.Color;
import android.view.View;

import com.tunyin.base.BaseInjectActivity;
import com.tunyin.mvp.contract.mine.FeedbackContract;
import com.tunyin.mvp.contract.mine.ResetPasswordContract;
import com.tunyin.mvp.model.UploadFileEntity;
import com.tunyin.mvp.presenter.mine.FeedbackPresenter;
import com.tunyin.utils.eye.Eyes;

import org.jetbrains.annotations.NotNull;

public class FeedbackActivity extends BaseInjectActivity<FeedbackPresenter> implements FeedbackContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_feed_back;
    }


    @Override
    public void uploadPicSus(@NotNull UploadFileEntity uploadFileEntity) {

    }

    @Override
    public void initWidget() {
        super.initWidget();
        Eyes.setStatusBarColor(this, Color.parseColor("#FFFFFF"), true);
    }

    public void onBack(View view) {
        finish();
    }
}
