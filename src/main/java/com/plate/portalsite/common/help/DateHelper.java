package com.plate.portalsite.common.help;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateHelper {

    static String pattern = "yyyy-MM-dd hh:mm:ss";

    static SimpleDateFormat sd = new SimpleDateFormat();
    public static String getCurrDateTime(){

        final Calendar now = Calendar.getInstance();
        SimpleDateFormat sd = new SimpleDateFormat(pattern);
        return sd.format(now.getTime());
    }
    public static String getCurrDateStr(){
        final Calendar now = Calendar.getInstance();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        return sd.format(now.getTime());
    }
}
