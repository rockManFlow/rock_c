package com.kuark.tool.advance.advance20190708;

import org.springframework.stereotype.Service;

import java.util.TimerTask;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/8 16:39
 */
@Service
public class RunTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("run RunTask");
    }
}
