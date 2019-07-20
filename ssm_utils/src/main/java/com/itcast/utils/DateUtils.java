package com.itcast.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /**
     * 日期类型转字符串类型
     * @param date
     * @param patt
     * @return
     */
    public static String date2String(Date date,String patt){
        DateFormat df = new SimpleDateFormat (patt);
        String dateStr1 = df.format ( date );
        return dateStr1;
    }

    /**
     * 字符串类型转日期类型
     * @param dateStr
     * @param patt
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String dateStr,String patt) throws ParseException {
        DateFormat df = new SimpleDateFormat (patt);
        Date date1 = df.parse ( dateStr );
        return date1;
    }
}
