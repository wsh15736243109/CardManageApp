package com.itboye.cardmanage.util;

import android.app.Activity;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class MobileStatusLabelUtil {
    //小米系统下状态栏文字颜色的修改
    public static boolean MIUISetStatusBarLightMode(Activity activity, boolean dark) {
        boolean result = false;
        Window window = activity.getWindow();
//        if (window != null) {
//            Class clazz = window.getClass();
//            try {
//                int darkModeFlag = 0;
//                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
//                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
//                darkModeFlag = field.getInt(layoutParams);
//                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
//                if (dark) {
//                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
//                } else {
//                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
//                }
//                result = true;
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && RomUtils.isMiUIV7OrAbove()) {
//                    //开发版 7.7.13 及以后版本采用了系统API，旧方法无效但不会报错，所以两个方式都要加上
//                    if (dark) {
//                        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//                    } else {
//                        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//                    }
//                }
//            } catch (Exception e) {
//
//            }
//        }
        return result;
    }

    // 魅族系统状态栏文字颜色修改
    private static boolean setFlymeLightStatusBar(Activity activity, boolean dark) {
        boolean result = false;
//        if (activity != null) {
//            try {
//                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
//                Field darkFlag = WindowManager.LayoutParams.class
//                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
//                Field meizuFlags = WindowManager.LayoutParams.class
//                        .getDeclaredField("meizuFlags");
//                darkFlag.setAccessible(true);
//                meizuFlags.setAccessible(true);
//                int bit = darkFlag.getInt(null);
//                int value = meizuFlags.getInt(lp);
//                if (dark) {
//                    value |= bit;
//                } else {
//                    value &= ~bit;
//                }
//                meizuFlags.setInt(lp, value);
//                activity.getWindow().setAttributes(lp);
//                result = true;
//            } catch (Exception e) {
//            }
//        }
        return result;
    }

    // 小米系统判断
    private static boolean isMiUIV6OrAbove() {
//        try {
//            final Properties properties = new Properties();
//            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
//            String uiCode = properties.getProperty(KEY_MIUI_VERSION_CODE, null);
//            if (uiCode != null) {
//                int code = Integer.parseInt(uiCode);
//                return code >= 4;
//            } else {
//                return false;
//            }
//
//        } catch (final Exception e) {
//            return false;
//        }
        return false;

    }

    //魅族系统判断
    private static boolean isFlymeV4OrAbove() {
//        String displayId = Build.DISPLAY;
//        if (!TextUtils.isEmpty(displayId) && displayId.contains("Flyme")) {
//            String[] displayIdArray = displayId.split(" ");
//            for (String temp : displayIdArray) {
//                //版本号4以上，形如4.x.
//                if (temp.matches("^[4-9]\\.(\\d+\\.)+\\S*")) {
//                    return true;
//                }
//            }
//        }
        return false;
    }

}
