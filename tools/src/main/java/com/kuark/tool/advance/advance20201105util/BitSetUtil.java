package com.kuark.tool.advance.advance20201105util;

import java.util.BitSet;

public class BitSetUtil {
    public static void main(String[] args) {
        //todo 看成是二进制数位置表示0/1 ，但真实存的数组数字仅是这些二进制位数对应的数字
        BitSet bitSet=new BitSet();

        //[0,2)会被设置成1   对应位置的0，1位会被设置为1，但实际数字存在下标0位的数字就可以表示
        bitSet.set(0,2,true);//words[0]=3 二进制：11 刚好是0，1位只为1了
        bitSet.set(4,true);
        bitSet.stream().forEach(b-> System.out.println("local:"+b+"|value:"+bitSet.get(b)));

        //转成2进制之后，右移6位，高位补0/1
//        System.out.println(7 >> 6);
    }
}
