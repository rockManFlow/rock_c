package com.kuark.tool.advance.advance20201105util.formal;

import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/**
 * 字符串进行16进制编码
 */
public class StringUtil {
    private static final Logger logger=Logger.getLogger(StringUtil.class);
    private static String hexString = "0123456789ABCDEF";
    //把utf-8字符串进行16进制编码
    public static String stringT16(String st) {
        StringBuilder context = new StringBuilder();
        st = new String(st.getBytes());
        byte[] b = st.getBytes();
        int[] in = new int[b.length];
        for (int i = 0; i < in.length; i++) {
            in[i] = b[i] & 0xff;
        }
        for (int j = 0; j < in.length; j++) {
            context.append(Integer.toString(in[j], 0x10));
        }
        return context.toString().toUpperCase();
    }
    //转16进制gbk编码
    public static String StrToHex(String str) {
        byte[] bytes = null;
        try {
            //按照指定的编码将指定的string编码到byte数组中，String(byte[] bytes, Charset charset)是解码
            bytes = str.getBytes("GBK");
        } catch (Exception e) {
            logger.error("coding error");
        }
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
        }
        return sb.toString();
    }
    //16解码gbk解码
    public static String HexToStr(String bytes) throws UnsupportedEncodingException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(
                bytes.length() / 2);
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
                    .indexOf(bytes.charAt(i + 1))));
        return new String(baos.toByteArray(),"GBK");
    }
}
