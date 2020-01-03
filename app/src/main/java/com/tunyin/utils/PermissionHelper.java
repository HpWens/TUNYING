package com.tunyin.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import androidx.core.app.ActivityCompat;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tunyin.widget.AskDialog;

import io.reactivex.functions.Consumer;

/**
 * create by wangrongchao
 * on 2019/11/14
 **/
public class PermissionHelper {

    /**
     * sd访问拍照权限
     *
     * @param onRequestPermissionResult
     */
    @SuppressLint("CheckResult")
    public void requestSDAndCameraPermissions(final Activity context, final OnRequestPermissionResult onRequestPermissionResult) {
        RxPermissions rxPermissions = new RxPermissions(context);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    onRequestPermissionResult.onResult(true);
                } else {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        showIntentDialog(context);
                    } else if (!ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA)) {
                        showIntentDialog(context);
                    }
                    onRequestPermissionResult.onResult(false);
                }
            }
        });
    }

    /**
     * 定位权限
     *
     * @param context
     * @param onRequestPermissionResult
     */
    public void requestLocation(final Activity context, final OnRequestPermissionResult onRequestPermissionResult) {
        RxPermissions rp = new RxPermissions(context);
        rp.request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            onRequestPermissionResult.onResult(true);
                        } else {
                            if (!ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                                showLocationDialog(context);
                            }
                            else if (!ActivityCompat.shouldShowRequestPermissionRationale(context, Manifest.permission.CAMERA)) {
                                showIntentDialog(context);
                            }
                            onRequestPermissionResult.onResult(false);
                        }
                    }
                }).dispose();
    }


    private void showLocationDialog(final Activity context) {
        String message = "你未开启定位权限,是否前往开启？";
        final AskDialog askDialog = AskDialog.with(context).setParams("提示", message);
        askDialog.setOnRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askDialog.dismiss();
                PermissionSetting.openPermissionPage(context);
            }
        });
        askDialog.show();


    }

    private void showIntentDialog(final Activity context) {
        String message = "你未开启sd卡访问和拍照权限,是否前往开启？";
        final AskDialog askDialog = AskDialog.with(context).setParams("提示", message);
        askDialog.setOnRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askDialog.dismiss();
//                PermissionSetting.openPermissionPage(context);
            }
        });
        askDialog.show();


    }

    public interface OnRequestPermissionResult {
        void onResult(boolean isSuccess);
    }


}
