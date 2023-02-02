package com.kuark.tool.model.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by caoqingyuan on 2017/7/31.
 */
public class DateTest {
    public static void main(String[] s) throws ParseException {
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        Date a=sim.parse("2017-08-01");
        Date date = dateOp(1, a);
        System.out.println("date="+sim.format(date));
        System.out.println(a.after(date));
    }
    public static Date dateOp(Integer day,Date a){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sdf.format(a);
        Calendar cd = Calendar.getInstance();
        try {
            cd.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cd.add(Calendar.DATE, -day);//减多少天
        Date date=cd.getTime();
        return date;
    }
}
