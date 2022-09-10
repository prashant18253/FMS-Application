package com.example.fmsio.utils;

public class DateTimeUtils {
    public static String unixToStandard(String unix){
        java.util.Date time = new java.util.Date(Long.valueOf(unix) * 1000);
        return String.valueOf(time).substring(0, 20);
    }
}
