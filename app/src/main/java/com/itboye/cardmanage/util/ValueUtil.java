package com.itboye.cardmanage.util;

import android.text.TextUtils;

/**
 * @Author:Create by Mr.w
 * @Date:2019/5/24 22:30
 * @Description:
 */
public class ValueUtil {
    public boolean isEmpty(Object value) {
        return TextUtils.isEmpty(value + "");
    }
}
