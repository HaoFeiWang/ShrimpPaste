package com.sppe.shrimppaste.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by WHF on 2017/11/12.
 */

public class TextUtil {

    private static final int MAX_TITLE_TEXT = 30;

    public static String dealAtricleTitle(String title) {
        if (title.length() > MAX_TITLE_TEXT) {
            return title.substring(0, MAX_TITLE_TEXT).concat("...");
        }
        return title;
    }

    public static String dealTime(String timeText) {

        int lastIndex = timeText.lastIndexOf(".");
        String time = timeText.substring(0, lastIndex);
        time = time.replace("T", "");

        long min = 60 * 1000;
        long hour = min * 60;
        long day = hour * 24;

        try {
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFromat = new SimpleDateFormat("HH:mm");

            Date objDate = dateTimeFormat.parse(time);
            long objTime = objDate.getTime();
            Calendar objCalendar = Calendar.getInstance();
            objCalendar.setTime(objDate);

            //今天 00:00:00
            Calendar curCalendarTemp = Calendar.getInstance();
            curCalendarTemp.set(Calendar.HOUR_OF_DAY, 0);
            curCalendarTemp.set(Calendar.MINUTE, 0);
            curCalendarTemp.set(Calendar.SECOND, 0);
            curCalendarTemp.set(Calendar.MILLISECOND, 0);

            if (objCalendar.before(curCalendarTemp)) {
                long value = curCalendarTemp.getTimeInMillis() - objTime;

                if (value < day) {
                    return "昨天 ".concat(timeFromat.format(objDate));
                } else if (value < day * 2) {
                    return "前天 ".concat(timeFromat.format(objDate));
                } else {
                    return dateFormat.format(objDate);
                }
            } else {
                long value = objTime - curCalendarTemp.getTimeInMillis();

                if (value < min) {
                    return "刚刚";
                } else if (value < hour) {
                    return String.valueOf(value / min).concat("分钟前");
                } else {
                    return String.valueOf(value / hour).concat("小时前");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return time;
        }
    }

}
