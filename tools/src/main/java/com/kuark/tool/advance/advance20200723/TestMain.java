package com.kuark.tool.advance.advance20200723;

import org.apache.commons.codec.digest.DigestUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rock
 * @detail
 * @date 2020/7/23 11:00
 */
public class TestMain {
    public static void main(String[] args) {
//        String st="345.33,456.456";
        String st="34533456.456";
        boolean contains = st.contains(",");
        System.out.println(contains);
        Pattern pattern = Pattern.compile("[0-9]*[\\.]*[0-9]*,[0-9]*[\\.]*[0-9]*");
//        Pattern pattern = Pattern.compile("[0-9]*,[0-9]*");
        Matcher isNum = pattern.matcher(st);
        System.out.println(isNum.matches());
        String pw="12345678";
        String salt="7bd6587d9cdc4904a7914fee8d2dfb93";
        String pass = DigestUtils.md5Hex(pw);
        System.out.println(pass);


    }
}
