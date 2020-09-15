package com.tunyin.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.tunyin.R;

public class SexDialog extends AlertDialog {

    OnClickListener clickListener;

    public SexDialog(Context context, OnClickListener listener) {
        super(context);
        clickListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        setContentView(R.layout.dialog_sex);

        findViewById(R.id.tv_man).setOnClickListener(v -> {
            clickListener.onClick(v, "男", this);
        });

        findViewById(R.id.tv_woman).setOnClickListener(v -> {
            clickListener.onClick(v, "女", this);
        });
    }

    public interface OnClickListener {
        void onClick(View view, String sex, SexDialog dialog);
    }
}
