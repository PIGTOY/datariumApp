package com.hanadocu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {

	private static TimeZone mySTZ = (TimeZone) TimeZone.getTimeZone("JST");
	private static Locale lc = new Locale("Locale.KOREAN", "Locale.KOREA");
	
	/**
     * 오늘 날짜(YYYYMMDD)
     *
     */
    public static String getDate() {

        String ch = "";

        try{

            TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
            TimeZone.setDefault(tz);
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            ch = sdf.format(d);

        } catch(Exception e) {
            log.error("[ERROR] getDate : {}", e);
        }

        return ch;
    }
    
    /**
     * 현재 년도
     */
    public static String getYear() {

        String ch = "";

        try{

            TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
            TimeZone.setDefault(tz);
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            ch = sdf.format(d);

        } catch(Exception e) {
            log.error("[ERROR] getYear : {}", e);
        }

        return ch;
    }
    
    /**
     * 현재 달
     */
    public static String getMonth() {

        String ch = "";

        try{

            TimeZone tz = new SimpleTimeZone( 9 * 60 * 60 * 1000, "KST" );
            TimeZone.setDefault(tz);
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("MM");
            ch = sdf.format(d);

        } catch(Exception e) {
            log.error("[ERROR] getMonth : {}", e);
        }

        return ch;
    }
}
