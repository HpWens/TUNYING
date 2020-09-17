package com.tunyin.ui.activity;

import android.content.Intent;
import android.widget.TextView;

import com.tunyin.R;
import com.tunyin.base.BaseInjectActivity;
import com.tunyin.mvp.contract.mine.GradeContract;
import com.tunyin.mvp.model.SelfBean;
import com.tunyin.mvp.model.mine.MyGradeEntity;
import com.tunyin.mvp.presenter.mine.GradePresenter;
import com.tunyin.ui.activity.mine.LoginActivity;
import com.tunyin.utils.eye.Eyes;
import com.tunyin.widget.RankProgressView;

import org.jetbrains.annotations.NotNull;

public class MyRankActivity extends BaseInjectActivity<GradePresenter> implements GradeContract.View {

    RankProgressView rankProgressView;
    TextView tvGrade;

    @Override
    public void initWidget() {
        super.initWidget();
        Eyes.translucentStatusBar(this, true);
        rankProgressView = findViewById(R.id.rank_progress);
        tvGrade = findViewById(R.id.tv_grade);
    }

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
    public void showError(@NotNull String msg) {
        super.showError(msg);
        if (msg.contains("Token") ||
                msg.contains("token")) {
            SelfBean.getInstance().setToken("");
            Intent intent = LoginActivity.newInstance(this);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void loadData() {
        super.loadData();

        mPresenter.getGrade();

        findViewById(R.id.iv_back).setOnClickListener(v -> {
            finish();
        });


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_rank;
    }

    @Override
    public void getGradeCallBack(@NotNull MyGradeEntity entity) {
        rankProgressView.refreshProgress(entity.wealthValue, entity.nextValue, entity.userGrade);
        tvGrade.setText(entity.userGrade + "");
    }
}
