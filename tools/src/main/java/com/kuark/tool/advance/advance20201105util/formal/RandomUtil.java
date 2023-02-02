package com.kuark.tool.advance.advance20201105util.formal;

import java.util.Random;

/**
 * @author rock
 * @detail 随机数生成
 * @date 2020/11/5 10:07
 */
public class RandomUtil {
    /**
     * 生成指定位数的随机数
     * @param len
     * @return
     */
    public static String getRandom3(int len) {
        //Math.pow(底数,几次方)
        int digit = (int) Math.pow(10, len - 1);
        //生成一个随机的int值，该值介于[0,n)的区间
        int rs = new Random().nextInt(digit * 10);
        if (rs < digit) {
            rs += digit;
        }
        return String.valueOf(rs);
    }
}
