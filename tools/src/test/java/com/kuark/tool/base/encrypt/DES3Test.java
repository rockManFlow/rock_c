package com.kuark.tool.base.encrypt;

import com.kuark.tool.base.encrypt.formal.DES3Encrypt;

/**
 * Created by caoqingyuan on 2017/4/26.
 */
public class DES3Test {
    public static void main(String[] args) {
        try {
            String test = "6222020200002432";
            String strKey="D543CB3EB6ADA879A8D5260E79976183AD3B915DFEADFD16";
            System.out.println("size="+strKey.length());
            String stren = DES3Encrypt.encrypt(strKey, test);
            System.out.println(stren);

            String strde = DES3Encrypt.decrypt(strKey, stren);
            System.out.println(strde);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
