package com.kuark.tool.advance.advance20201105util;

import java.security.MessageDigest;

/**
 * @author rock
 * @detail
 * @date 2019/11/13 10:48
 */
public class DigestUtil {

    /**
     * 获取MD5值
     * @param str
     * @return
     */
    public static String getMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要下面的MD5也可换成SHA或SHA-1  SHA也是一种和MD5类似的单向散列加密算法
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return byte2hex(md.digest());
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 16进制编码
     * @return
     */
    public static String byte2hex(byte[] b){
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++){
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1){
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }
}
