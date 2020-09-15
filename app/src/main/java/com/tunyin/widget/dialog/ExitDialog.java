package com.tunyin.widget.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tunyin.R;
import com.vondear.rxui.view.dialog.RxDialog;

public class ExitDialog extends RxDialog {

    TextView tvMan;
    TextView tvWoman;
    TextView tvCancel;

    private OnItemClickListener itemClickListener;

    public ExitDialog(Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    public ExitDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    public ExitDialog(Context context, OnItemClickListener listener) {
        super(context);
        itemClickListener = listener;
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sex_select, null);
        setContentView(view);
        mLayoutParams.gravity = Gravity.BOTTOM;
        mLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        getWindow().setWindowAnimations(R.style.dialogBottomAnim);

        tvMan = view.findViewById(R.id.tv_man);
        tvWoman = view.findViewById(R.id.tv_woman);
        tvCancel = view.findViewById(R.id.tv_cancel);

        tvMan.setOnClickListener(v -> {
        });

        tvWoman.setOnClickListener(v -> {
            if (itemClickListener != null) itemClickListener.onClick(true);
            dismiss();
        });

        tvCancel.setOnClickListener(v -> dismiss());
    }

    public interface OnItemClickListener {
        void onClick(boolean isExit);
    }
}
