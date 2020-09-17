package com.tunyin.ui.dialog;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.tunyin.R;
import com.tunyin.ToastUtils;
import com.vondear.rxui.view.dialog.RxDialog;

public class EditAmountDialog extends RxDialog {

    private OnClickListener clickListener;
    private EditText etAmount;

    public EditAmountDialog(Context context, OnClickListener listener) {
        super(context);
        clickListener = listener;
        initView(context);
    }

    public void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_amount, null);
        setContentView(view);

        etAmount = view.findViewById(R.id.et_amount);

        view.findViewById(R.id.tv_confirm).setOnClickListener(v -> {
            String amount = etAmount.getText().toString().trim();
            if (TextUtils.isEmpty(amount)) {
                ToastUtils.INSTANCE.showToast("金额不能为空");
            } else {
                clickListener.onConfirm(EditAmountDialog.this, amount);
            }
        });
    }

    public interface OnClickListener {
        void onConfirm(EditAmountDialog dialog, String amount);
    }

}
