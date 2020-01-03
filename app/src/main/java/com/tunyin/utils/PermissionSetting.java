package com.tunyin.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;


/**
 * Created by yiang on 2018/4/4.
 */

public class PermissionSetting {
    private static final String PACKAGENAME = "com.fat.huopin";

    public static void openPermissionPage(Context context) {
        if (SystemUtil.isMiuiOs()) {
            miuiPermissionPage(context);
        } else {
            context.startActivity(getAppDetailSettingIntent());
        }
    }


    /**
     * 小米权限界面
     *
     * @param context
     */
    private static void miuiPermissionPage(Context context) {

        try { // MIUI 8
            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
//            localIntent.setClassName("com.miui.securitycenter", "com.android.packageinstaller.permission.ui.ManagePermissionsActivity");
            localIntent.putExtra("extra_pkgname", PACKAGENAME);
            context.startActivity(localIntent);
        } catch (Exception e) {
            try { // MIUI 5/6/7
                Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                localIntent.putExtra("extra_pkgname", PACKAGENAME);
                context.startActivity(localIntent);
            } catch (Exception e1) { // 否则跳转到应用详情
                context.startActivity(getAppDetailSettingIntent());
            }
        }
    }


    /**
     * 华为的权限管理页面
     */
    private static void huaweiPermissionPage(Context context) {
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("packageName", PACKAGENAME);
        ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
        intent.setComponent(comp);
        context.startActivity(intent);
    }


    /**
     * 应用详情界面
     *
     * @return
     */
    private static Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", PACKAGENAME, null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", PACKAGENAME);
        }
        return localIntent;
    }

}
