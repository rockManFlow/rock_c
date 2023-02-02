package com.kuark.tool.base.test;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by cc on 2016/5/16.
 */
public class Log4jTest {
    private static Logger logger = Logger.getLogger(Log4jTest.class);

    @Test
    public void test1() {
        {
            flag1:
            for (int i = 0; i < 10; i++) {
                if (i == 5) break flag1;
                System.out.println("   i=" + i);
            }
            System.out.println("sdsds=");
        }

    }

    public void hg() {
        int a = 0;
        ts:
        {
            if (a == 10) {
                System.out.println("==f==");
            }
            loop:
            while (a < 20) {
                a++;
                System.out.println("a=" + a);
                if (a == 10) {
                    System.out.println("b=" + a);
                    continue loop;
                }
            }
        }
        System.out.println("finish");
    }

    public void tts() {
        loop1:
        for (int I1 = 0; I1 < 10; I1++) {
            for (int I2 = 0; I2 < 20; I2++) {
                continue loop1;
            }
        }

    }
}
