package com.meis.base.mei.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentActivity;

/**
 * desc:
 * author: ws
 * date: 2018/4/19.
 */

public class MeiCompatDialog extends AppCompatDialogFragment implements IMeiCompatDialog {

    final MeiCompatDialogDelegate mDelegate = new MeiCompatDialogDelegate(this);

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return mDelegate.onCreateDialog(super.onCreateDialog(savedInstanceState));
    }

    protected void setFullScreen() {
        mDelegate.setFullScreen();
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return mDelegate.findViewById(id);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mDelegate.onDismiss(dialog);
    }

    /**
     * 沉浸模式下调用这个方法
     *
     * @param activity
     */
    public void showImmersive(FragmentActivity activity) {
        mDelegate.showImmersive(activity);
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        mDelegate.setOnDismissListener(onDismissListener);
    }

    @Override
    public void dismiss() {
        mDelegate.dismiss();
    }
}
