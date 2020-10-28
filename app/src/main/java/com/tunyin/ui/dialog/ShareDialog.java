package com.tunyin.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.tunyin.R;

public class ShareDialog extends AlertDialog {

    OnClickListener clickListener;

    public ShareDialog(Context context, OnClickListener listener) {
        super(context);
        clickListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        getWindow().setGravity(Gravity.BOTTOM);
        setContentView(R.layout.dialog_share);

        findViewById(R.id.share_wechat_layout).setOnClickListener(v -> {
            clickListener.onWechat();
            dismiss();
        });

        findViewById(R.id.share_circle_layout).setOnClickListener(v -> {
            clickListener.onCircle();
            dismiss();
        });

        findViewById(R.id.tv_cancel).setOnClickListener(v -> {
            dismiss();
        });
    }

    public interface OnClickListener {
        void onWechat();

        void onCircle();
    }

}
