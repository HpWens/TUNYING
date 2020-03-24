package com.tunyin.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.FileProvider;

import com.tunyin.R;

import java.io.File;

public class VersionDialog extends AlertDialog {

    TextView tvUpdate;

    OnClickListener onClickListener;

    public VersionDialog(Context context, OnClickListener listener) {
        super(context);
        onClickListener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        setContentView(R.layout.dialog_version);
        setCanceledOnTouchOutside(false);

        tvUpdate = findViewById(R.id.tv_update);

        tvUpdate.setOnClickListener(v -> {
            if (onClickListener != null) onClickListener.onClick(v, this);
        });
    }

    public interface OnClickListener {
        void onClick(View view, VersionDialog dialog);
    }

    public static void installApp(Context context, String filePath) {
        context.startActivity(getInstallAppIntent(context, filePath));
    }

    public static Intent getInstallAppIntent(Context context, String filePath) {
        //apk文件的本地路径
        File apkfile = new File(filePath);
        if (!apkfile.exists()) {
            return null;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri contentUri = getUriForFile(context, apkfile);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        return intent;
    }

    public static Uri getUriForFile(Context mContext, File file) {
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= 24) {
            fileUri = FileProvider.getUriForFile(mContext, "com.fat.weishuo.fileProvider", file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }
}
