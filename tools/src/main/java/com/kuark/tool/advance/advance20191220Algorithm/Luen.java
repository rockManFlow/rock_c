package com.kuark.tool.advance.advance20191220Algorithm;

/**
 * @author rock
 * @detail 卢恩算法（兼容奇偶）：奇数和偶数计算规则不同，需颠倒一下
 * @date 2022/6/15 14:51
 */
public class Luen {
    /**
     * @return
     */
    private static String luen(String cardNo) {
        boolean isEven=cardNo.length()%2==0;
        int count = 0;
        char[] chars = cardNo.toCharArray();
        for(int i = 0; i < chars.length;i++){
            int num = Integer.parseInt(chars[i]+"");
            boolean result=isEven?(i % 2 == 0):(i % 2 != 0);
            if (result){
                count += num;
            }else {
                num *=2;
                if (num > 9){
                    count = count+num-9;
                }else {
                    count += num;
                }
            }
        }
        int lastCardNo = count % 10;
        if (lastCardNo == 0){
            return cardNo + "0";
        }
        lastCardNo = 10 - lastCardNo;
        return cardNo+lastCardNo;
    }

    static boolean checkLuhn(String cardNo)
    {
        int nDigits = cardNo.length();
        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--)
        {
            int d = cardNo.charAt(i) - '0';
            if (isSecond == true)
                d = d * 2;
            // We add two digits to handle
            // cases that make two digits
            // after doubling
            nSum += d / 10;
            nSum += d % 10;
            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }
}
