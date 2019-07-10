package com.itboye.cardmanage.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtils {
    public static String timeFormat(long times, String pattern) {
        return new SimpleDateFormat(pattern).format(times);
    }

    public static String getStrFormat(String str, int length) {
        StringBuffer stringBuffer = new StringBuffer(str);
        while (stringBuffer.length() < length) {
            stringBuffer.insert(0, "0");
        }

        return stringBuffer.toString();
    }

    public static String parseTime(String time, String inPattern, String outPattern) {
        SimpleDateFormat intFormat = new SimpleDateFormat(inPattern);
        SimpleDateFormat outFormat = new SimpleDateFormat(outPattern);
        try {
            long inMill = intFormat.parse(time).getTime();
            return outFormat.format(inMill);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static long parseTime2Long(String time, String inPattern) {
        SimpleDateFormat intFormat = new SimpleDateFormat(inPattern);
        try {
            long inMill = intFormat.parse(time).getTime();
            return inMill;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
