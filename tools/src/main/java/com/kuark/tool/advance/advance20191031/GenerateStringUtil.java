package com.kuark.tool.advance.advance20191031;

import java.util.Random;

/**
 * @author rock
 * @detail 随机生成指定长度的字母串
 * @date 2019/11/13 10:19
 */
public class GenerateStringUtil {
    private static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 由大小写字母、数字组成的随机字符串
     * @param length
     * @return
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }

    /**
     * 由大小写字母组成的随机字符串
     * @param length
     * @return
     */
    public static String generateLetterString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(letterChar.charAt(random.nextInt(letterChar.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args){
        String letterString = generateLetterString(10);
        System.out.println(letterString);
    }

}
