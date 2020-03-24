package com.tunyin.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tunyin.R;

public class CacheDialog extends AlertDialog {

    TextView tvCancel;
    TextView tvConfirm;

    OnClickListener onClickListener;

    public CacheDialog(Context context, OnClickListener listener) {
        super(context);
        onClickListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        setContentView(R.layout.dialog_cache);

        tvCancel = findViewById(R.id.tv_cancel);
        tvConfirm = findViewById(R.id.tv_confirm);

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
