package com.geektrust.makespace.util;

import java.sql.Time;

public class TimeUtil {

    private static final int MAX_HOUR=23;
    private static final int MIN_HOUR = 0;
    private static final int MAX_MINUTE = 59;
    private static final int MIN_MINUTE = 0;

    public static Time getHHMMFormat(String time) {
        String[] timeArr = time.split(":");
        int hour = Integer.parseInt(timeArr[0]);
        int minutes = Integer.parseInt(timeArr[1]);
        return new Time(hour, minutes, 0);
    }

    public static boolean isValidTimeFormat(String time) {
        if(time==null || time.isEmpty()) return false;
        String[] timeArr = time.split(":");
        if(timeArr.length!=2) return false;
        int hour = Integer.parseInt(timeArr[0]);
        int minutes = Integer.parseInt(timeArr[1]);
        return isValidHour(hour) && isValidMinute(minutes);
    }

    private static boolean isValidHour(int hour) {
        return hour>=MIN_HOUR && hour <= MAX_HOUR;
    }

    private static boolean isValidMinute(int minute) {
        return minute>=MIN_MINUTE && minute <= MAX_MINUTE;
    }
}
