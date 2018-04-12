package com.weheros.framework.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil
{
    public static Date formatDateToString(String strDate)
    {
        Date date = null;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(strDate);
        }
        catch (Exception e)
        {
            date = new Date();
        }
        return date;
    }
    
 
    
    public static String formatDateHms(Date date)
    {
        String strDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            strDate = sdf.format(date);
        }
        catch (Exception e)
        {
            strDate = sdf.format(new Date());
        }
        return strDate;
    }
  
    
   
    
    /**
     * 判断是否是日期并将2010-5-2转化2010-05-02
     * 
     * @param date
     * @return 转化后的字符串或当前日期的字符串
     */
    public static String forDateString(String date)
    {
        String strDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            strDate = sdf.format(sdf.parse(date));
        }
        catch (Exception e)
        {
            strDate = sdf.format(new Date());
        }
        return strDate;
    }
    
    /**
     * 判断是否是日期并将2010-5-2 HH:mm:ss转化2010-05-02 HH:mm:ss
     * 
     * @param date
     * @return 转化后的字符串或当前日期的字符串
     */
    public static String forDateStringHms(String date)
    {
        String strDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            strDate = sdf.format(sdf.parse(date));
        }
        catch (Exception e)
        {
            strDate = sdf.format(new Date());
        }
        return strDate;
    }
 
    
  
   
}
