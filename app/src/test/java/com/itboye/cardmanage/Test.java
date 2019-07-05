package com.itboye.cardmanage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    @org.junit.Test
    public void ccc() {
        String num1 = "0.60";
        String num2 = "2.00";
        String num3 = "1";
        System.out.println(String.format("%.3f", Double.parseDouble(num3) * Double.parseDouble(num1) - Double.parseDouble(num2)));
    }

    @org.junit.Test
    public void time() {
        long beginTime = Long.parseLong("075855");//
        long endTime = beginTime + 5;
        String lastTime = parseTime(endTime + "", "HHmmss", "HHmmss");
        System.out.println(lastTime);

        long beginTimeO = parseTimeToLong("121256", "HHmmss");
        long endTimeO = parseTimeToLong("121300", "HHmmss");
        long duration = (endTimeO - beginTimeO) / 1000;
        System.out.println(duration + "：时间间隔");
    }

    /**
     * @param time         时间
     * @param inTimePatten 传入时间格式
     * @return
     */
    public static long parseTimeToLong(String time, String inTimePatten) {
        SimpleDateFormat inFormater = new SimpleDateFormat(inTimePatten);
        Date inDate = null;
        try {
            inDate = inFormater.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return inDate.getTime();
    }

    public static String parseTime(String time, String inTimePatten,
                                   String outTimePatten) {
        SimpleDateFormat inFormater = new SimpleDateFormat(inTimePatten);
        Date inDate = null;
        try {
            inDate = inFormater.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormater = new SimpleDateFormat(outTimePatten);
        String outTime = outFormater.format(inDate.getTime());
        return outTime;
    }
}

