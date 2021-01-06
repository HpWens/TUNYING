package com.tunyin.base;

import android.content.Intent;
import android.text.TextUtils;

import com.tunyin.BaseContract;
import com.tunyin.NetworkUtils;
import com.tunyin.ToastUtils;
import com.tunyin.mvp.model.BaseEntity;
import com.tunyin.mvp.model.SelfBean;
import com.tunyin.ui.activity.mine.LoginActivity;
import com.tunyin.utils.AppUtils;

import io.reactivex.subscribers.ResourceSubscriber;

public abstract class BaseSubscriberPro<M> extends ResourceSubscriber<BaseEntity<M>> {

    public abstract void onSuccess(M m);
//    public abstract void onError(String msg);

    private BaseContract.BaseView mView;

    public BaseSubscriberPro(BaseContract.BaseView view) {
        this.mView = view;
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!NetworkUtils.INSTANCE.isConnected(AppUtils.getAppContext())) {
            ToastUtils.INSTANCE.showToast("当前网络连接异常");
            return;
        }
    }

    @Override
    public void onNext(BaseEntity<M> mBaseEntity) {
        if (mView != null) {
            if (TextUtils.equals("400", mBaseEntity.code)) {
                if (mBaseEntity.code.contains(": ")) {
                    String message = mBaseEntity.code.substring(mBaseEntity.code.indexOf(": ") + 1);
                    ToastUtils.INSTANCE.showToast(message);
                } else {
                    ToastUtils.INSTANCE.showToast(mBaseEntity.desc);
                }
                //ToastUtils.INSTANCE.showToast(mBaseEntity.desc);
            } else if (TextUtils.equals("401", mBaseEntity.code)) {
                SelfBean.getInstance().setToken("");
                Intent intent = LoginActivity.newInstance(AppUtils.getAppContext());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                AppUtils.getAppContext().startActivity(intent);
            } else {
                onSuccess(mBaseEntity.content);
            }
        }

    }

    //    @Override
//    public void onNext(M response) {
//        if (mView != null) {
//            if (TextUtils.equals("400", response.code)) {
//                ToastUtils.INSTANCE.showToast(response.desc);
//            } else {
//                onSuccess(response);
//            }
//        }
//
//    }

    //    @Override
//    public void onNext(M m) {
//        if (mView != null) {
//            if (m.code.equals("400")) {
//                ToastUtils.INSTANCE.showToast(m.desc);
//            } else {
//                onSuccess(m);
//            }
//        }
//
//    }

    @Override
    public void onError(Throwable t) {
        if (mView != null) {
            mView.showError(t.getMessage());
//            onError(t.getMessage());
        }

    }

    @Override
    public void onComplete() {
        int i = 0;

    }
}
