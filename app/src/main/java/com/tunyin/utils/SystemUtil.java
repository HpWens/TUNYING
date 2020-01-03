package com.tunyin.utils;

import android.os.Build;
import android.text.TextUtils;


/**
 * Created by 15 on 2017/10/12.
 */

public class SystemUtil {
    public static final String SYS_EMUI = "sys_emui";
    public static final String SYS_MIUI = "sys_miui";
    public static final String SYS_FLYME = "sys_flyme";

    /**
     * 获取系统信息，这边主要是对魅族小米和华为进行区分
     *
     * @return
     */
    public static String getSystem() {
        String manufacturer = Build.MANUFACTURER;
        String SYS = null;

        if (TextUtils.equals(manufacturer.toLowerCase(), "xiaomi")) {
            SYS = SYS_MIUI;//小米
        } else if (TextUtils.equals(manufacturer.toLowerCase(), "huawei")) {
            SYS = SYS_EMUI;//华为
        } else if (TextUtils.equals(manufacturer.toLowerCase(), "flyme")) {
            SYS = SYS_FLYME;//魅族
        }
        return SYS;
    }

    public static boolean isMiuiOs() {
        return TextUtils.equals(getSystem(), SYS_MIUI);
    }
}