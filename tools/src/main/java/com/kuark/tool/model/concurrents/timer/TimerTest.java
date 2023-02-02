package com.kuark.tool.model.concurrents.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

/**
 * Created by caoqingyuan on 2017/7/27.
 */
public class TimerTest {
    public static void main(String[] s) throws ParseException {
        Timer timer=new Timer(true);
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=sim.parse("2017-07-27 09:08:00");
        timer.schedule(new MyTask(),date);
    }
}
