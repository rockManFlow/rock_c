package com.kuark.tool.base.encrypt.formal;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Base64作用：
 * 一般服务器返回来的加密后数据都是要Base64编码的
 * （否则容易丢失数据，抛出异常：javax.crypto.IllegalBlockSizeException:
 * last block incomplete in decryption）。所以要用Base64解码。
 * 简单的说是把二进制数据编码成ASCII字符
 * Created by caoqingyuan on 2016/9/7.
 */
public class Base64Util {
    //base64 编码
    public static String base64Encode(String context) throws UnsupportedEncodingException {
        if(context==null){
            return null;
        }
        return new BASE64Encoder().encode(context.getBytes("UTF-8"));
    }
    //base64 解码
    public static String base64Decode(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }
}
