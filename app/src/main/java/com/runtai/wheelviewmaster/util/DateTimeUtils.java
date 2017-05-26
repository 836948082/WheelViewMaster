package com.runtai.wheelviewmaster.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DateTimeUtils {

    /**
     * 获取年月日、星期
     *
     * @param days --2017-03-04周六
     * @return ArrayList<String>
     */
    public static ArrayList<String> getDateAndWeek(int days) {
        ArrayList<String> list = new ArrayList<String>();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String Week = "";
        for (int i = 0; i < days; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, i);
            switch (calendar.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.SUNDAY:
                    Week = "周天";
                    break;
                case Calendar.MONDAY:
                    Week = "周一";
                    break;
                case Calendar.TUESDAY:
                    Week = "周二";
                    break;
                case Calendar.WEDNESDAY:
                    Week = "周三";
                    break;
                case Calendar.THURSDAY:
                    Week = "周四";
                    break;
                case Calendar.FRIDAY:
                    Week = "周五";
                    break;
                case Calendar.SATURDAY:
                    Week = "周六";
                    break;
                default:
                    break;
            }
            list.add(sf.format(calendar.getTime()) + Week);
        }
        return list;
    }

    /**
     * 获取小时、分钟
     */
    public static ArrayList<String> getHourAndMinite() {
        ArrayList<String> list = new ArrayList<String>();
        int inittime = 60 * 9 - 30;
        int hour = 0;
        int minute = 0;
        String newHour = "";
        String newMinute = "";
        for (int i = 0; i < 23; i++) {
            inittime += 30;
            hour = inittime / 60;
            minute = inittime % 60;
            if (hour < 10) {
                newHour = "0" + hour;
            } else {
                newHour = "" + hour;
            }
            if (minute < 10) {
                newMinute = "0" + minute;
            } else {
                newMinute = "" + minute;
            }
            list.add(newHour + ":" + newMinute);
        }
        return list;
    }

    /**
     * 移除现在之前的时间
     *
     * @param list--ArrayList<String>
     * @return ArrayList<String>
     */
    public static ArrayList<String> removeBeforeNowTime(ArrayList<String> list) {
        Calendar cal = Calendar.getInstance();// 当前日期
        int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
        int minute = cal.get(Calendar.MINUTE);// 获取分钟
        int minuteOfday = hour * 60 + minute;// 从0:00分开始到目前为止的分钟数
        int minMinute = 420;// (420为07:00时对应的分钟数) 7:00-7:30 对应可以预约时间为09:00，依次类推
        int maxMinute = 450;// (450为07:30时对应的分钟数) 7:00-7:30 对应可以预约时间为09:00，依次类推

        for (int i = 0; i < 23; i++) {
            if (minMinute <= minuteOfday && minuteOfday <= maxMinute) {
                break;
            } else {
                if (i == 22) {
                    list.remove(0);
                    list.add("已约满");
                } else {
                    list.remove(0);
                }
            }
            minMinute += 30;
            maxMinute += 30;
        }
        return list;
    }

}
