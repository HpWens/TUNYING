package com.tunyin.ui.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.tunyin.R;
import com.vondear.rxui.view.dialog.RxDialog;

public class RegisterSuccessDialog extends RxDialog {

    private OnClickListener clickListener;

    public RegisterSuccessDialog(Context context, OnClickListener listener) {
        super(context);
        clickListener = listener;
        initView(context);
        setCanceledOnTouchOutside(false);
    }

    public void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_register_success, null);
        setContentView(view);

        view.findViewById(R.id.iv_close).setOnClickListener(v -> clickListener.onClose(this));
    }

    public interface OnClickListener {
        void onClose(RegisterSuccessDialog dialog);
    }
}
