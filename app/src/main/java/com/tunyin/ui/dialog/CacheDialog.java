package com.tunyin.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.tunyin.R;

public class CacheDialog extends AlertDialog {

    TextView tvCancel;
    TextView tvConfirm;
    TextView tvContent;

    OnClickListener onClickListener;

    String hintContent;

    public CacheDialog(Context context, OnClickListener listener) {
        super(context);
        onClickListener = listener;
    }

    public CacheDialog(Context context, OnClickListener listener, String hint) {
        super(context);
        onClickListener = listener;
        hintContent = hint;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        setContentView(R.layout.dialog_cache);

        tvCancel = findViewById(R.id.tv_cancel);
        tvConfirm = findViewById(R.id.tv_confirm);
        tvContent = findViewById(R.id.tv_content);

        if (!TextUtils.isEmpty(hintContent)) {
            tvContent.setText(hintContent);
        }

        tvCancel.setOnClickListener(v -> {
            dismiss();
        });

        tvConfirm.setOnClickListener(v -> {
            onClickListener.onClick(v, this);
        });
    }

    public interface OnClickListener {
        void onClick(View view, CacheDialog dialog);
    }
}
