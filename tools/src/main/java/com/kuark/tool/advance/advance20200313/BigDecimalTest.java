package com.kuark.tool.advance.advance20200313;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author caoqingyuan
 * @detail
 * @date 2020/3/25 15:13
 */
public class BigDecimalTest {

    public static void main(String[] args) {
        testBigDecimalMode();
    }
    public static void testBigDecimalMode(){
        double dayRate = 0.08;
        double amt = 1000;
        int diff = 1;

        double interestAmt = amt*dayRate/100*diff;
        BigDecimal tInterestAmt = new BigDecimal(interestAmt).setScale(2 , RoundingMode.UP);
        System.out.println(interestAmt);
        System.out.println(tInterestAmt);

        BigDecimal a = new BigDecimal("0.8");
        System.out.println("结果0.80="+a.setScale(2, RoundingMode.UP));
        BigDecimal b = new BigDecimal(0.8);
        System.out.println("结果0.81="+b.setScale(2 , RoundingMode.UP));
        //todo 对于BigDecimal中使用数字来构造，再进行精度操作的时候会出现精度不准问题
        //todo 而string不会出现问题

        BigDecimal c = new BigDecimal(100.8);
        System.out.println(c.setScale(2 , RoundingMode.UP));
    }
}
