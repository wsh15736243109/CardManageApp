package com.itboye.cardmanage;

public class Test {
    @org.junit.Test
    public void ccc() {
        String num1 = "0.60";
        String num2 = "2.00";
        String num3 = "1";
        System.out.println(String.format("%.3f", Double.parseDouble(num3) * Double.parseDouble(num1) - Double.parseDouble(num2)));
    }
}
