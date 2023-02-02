package com.kuark.tool.advance.advance20201111;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rock
 * @detail 进制转换
 * @date 2021/3/19 11:07
 */
public class SysConvertMain {
    public static void main(String[] args) {
//        int num=14;
//        int temp=num&15;
//        System.out.println(temp);

        //转换成十六进制。 二进制，八进制，也有类似的方法。
//        Integer.toHexString(1024);
//        System.out.println(Integer.parseInt("400",16)); //将16进制的400转换成十进制
//        System.out.println(Integer.valueOf("400",8));//将8进制的400转换成十进制
//
//
        String s = parseTo16(100);
        System.out.println(s);
    }

    /**
     * 十进制转16进制实现，其他的可以类似实现
     * @param num
     * @return
     */
    public static String parseTo16(Integer num){
        //1. 判断，如果是0的话，就直接返回0
        if(num==0){
            return "0";
        }
        //指定字符串
        char [] hexs={'0','1','2','3','4','5','6','7','8','9',
                'A','B','C','D','E','F'};

        List<Character> sbList=new ArrayList<>();
        while (num>0) {  //不知道要运行几次。
            //3. 将这些数字除以16,得到余数
            int temp=num&15;
            sbList.add(hexs[temp]);
            num=num>>>4;//除以16,得到商。
        }

        StringBuilder sb=new StringBuilder();
        for(int i=sbList.size()-1;i>=0;i--){
            sb.append(sbList.get(i));
        }

        return sb.toString();
    }
}
