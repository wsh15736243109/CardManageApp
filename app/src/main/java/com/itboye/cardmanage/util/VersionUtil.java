package com.itboye.cardmanage.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.itboye.cardmanage.app.App;

/**
 * VersionUtil
 * <p>
 * Created by Mr.w on 2017/11/17.
 * <p>
 * 版本      ${version}
 * <p>
 * 修改时间
 * <p>
 * 修改内容
 */


public class VersionUtil {
    /**
     * 获取App的版本号
     *
     * @return 返回App的版本号
     */
    public static String getVersionCode() {
        int appVersion = 0;
        try {

            PackageManager pm = App.getInstance()
                    .getPackageManager();
            PackageInfo info = pm.getPackageInfo(App.getInstance()
                    .getPackageName(), 0);
            appVersion = info.versionCode;
        } catch (Exception e) {

        }
        return "" + appVersion;
    }

    /**
     * 获取App的版本号
     *
     * @return 返回App的版本号
     */
    public static String getVersionName() {
        String appVersion = "";
        try {

            PackageManager pm = App.getInstance()
                    .getPackageManager();
            PackageInfo info = pm.getPackageInfo(App.getInstance()
                    .getPackageName(), 0);
            appVersion = info.versionName;
        } catch (Exception e) {

        }
        return "" + appVersion;
    }
}
