package com.itboye.cardmanage.util;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

public class WebCookieUtil {
    /**
     * 给WebView同步Cookie
     *
     * @param context 上下文
     * @param url     可以使用[domain][host]
     */
    public static void syncCookie(Context context, String url) {

        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookie();
        cookieManager.setCookie(url, "sid=" + UserUtil.getUserInfo().getSid());
        cookieManager.setCookie(url, "uid=" + UserUtil.getUserInfo().getId());
        CookieSyncManager.getInstance().sync();
    }
}
