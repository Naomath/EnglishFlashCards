package com.example.gotounaoto.myapplication.classes;

import java.util.Calendar;

/**
 * Created by gotounaoto on 2018/01/03.
 */

public class MakeDateString {
    public static String makeDate(){
        Calendar calendar = Calendar.getInstance();
        //それぞれのものを文字列に変換する
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        //monthは一月が0
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        StringBuffer buffer = new StringBuffer();
        buffer.append(year);
        buffer.append("-");
        buffer.append(month);
        buffer.append("-");
        buffer.append(day);
        String date_string = buffer.toString();
        return date_string;
    }
}
