package com.tunyin.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.tunyin.R;


/**
 * create by wangrongchao
 * on 2019/11/14
 **/
public class AskDialog {

    private static Context mActivity;
    private Dialog dialog;
    private TextView tvTitle;
    private TextView tvContent;
    private TextView tvCancel;
    private TextView tvConfirm;

    public static AskDialog with(Context activity) {
        mActivity = activity;
        return new AskDialog(activity);
    }


    private AskDialog(Context activity) {
        init(activity);
    }


    private void init(Context context) {

        // 智库课堂
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_ask, null);
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(mActivity);
        localBuilder.setView(view);
        dialog = localBuilder.create();
        tvTitle = view.findViewById(R.id.ask_tv_title);
        tvContent = view.findViewById(R.id.ask_tv_content);
        tvCancel = view.findViewById(R.id.ask_tv_cancel);
        tvConfirm = view.findViewById(R.id.ask_tv_confirm);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public AskDialog setParams(String title, String content) {
        tvTitle.setText(title);
        tvContent.setText(content);
        return this;
    }

    public AskDialog setCancelAble(boolean cancelAble) {
        dialog.setCancelable(cancelAble);
        return this;
    }

    public AskDialog setParams(String content) {
        tvTitle.setText("提示");
        tvContent.setText(content);
        return this;
    }

    public AskDialog setButtonContent(String left, String right) {
        if (!TextUtils.isEmpty(left)) {
            tvCancel.setText(left);
        }


        if (!TextUtils.isEmpty(right)) {
            tvConfirm.setText(right);
        }

        return this;
    }

    public Dialog show() {
        dialog.show();
        return dialog;
    }

    public Dialog create() {
        return dialog;
    }

    public boolean isShowing() {
        if (dialog == null) return false;
        return dialog.isShowing();
    }

    public Dialog dismiss() {
        dialog.dismiss();
        return dialog;
    }

    public AskDialog setOnRightClick(View.OnClickListener listener) {
        tvConfirm.setOnClickListener(listener);
        return this;
    }

    public AskDialog setOnleftClick(View.OnClickListener listener) {
        tvCancel.setOnClickListener(listener);
        return this;
    }
}

