package com.itboye.cardmanage.util;

import java.text.SimpleDateFormat;

public class TimeUtils {
    public static String timeFormat(long times, String pattern) {
        return new SimpleDateFormat(pattern).format(times);
    }
}
