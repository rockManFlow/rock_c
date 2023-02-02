package com.kuark.tool.base.encrypt;

/**
 * Created by caoqingyuan on 2017/4/26.
 */
public class SecurityUtilTest {
//    public static void main(String[] args) throws Exception {
//        String str = "数据加密的基本过程就是对原来为明文的文件或数据按某种算法进行处理，使其成为不可读的一段代码，通常称为“密文”，"
//                + "使其只能在输入相应的密钥之后才能显示出本来内容，通过这样的途径来达到保护数据不被非法人窃取、阅读的目的。 "
//                + "该过程的逆过程为解密，即将该编码信息转化为其原来数据的过程。";
//        str+=str;str+=str;str+=str;
//        String PWD = "SecurityUtil.PWD";
//        System.out.println("原文:[" + str.length() + "]" + str);
//        System.out.println("==MD5===============");
//        System.out.println(MD5(str));
//        System.out.println("==Base64============");
//        String strBase64 = Base64Encode(str);
//        System.out.println("加密:[" + strBase64.length() + "]" + strBase64);
//        System.out.println("解密:" + Base64Decode(strBase64));
//        System.out.println("==Aes============");
//        String strAes = AesEncrypt(str, PWD);
//        System.out.println("加密:[" + strAes.length() + "]" + strAes);
//        System.out.println("解密:" + AesDecrypt(strAes, PWD));
//        System.out.println("==Des==============");
//        String strDes = desEncrypt(str, PWD);
//        System.out.println("加密:[" + strDes.length() + "]" + strDes);
//        System.out.println("解密:" + desDecrypt(strDes, PWD));
//        System.out.println("==3Des==============");
//        String str3Des = threeDesEncrypt(str, PWD);
//        System.out.println("加密:[" + str3Des.length() + "]" + str3Des);
//        System.out.println("解密:" + threeDesDecrypt(str3Des, PWD));
//
//        //==========================================
//
//        long t1=System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++)
//            MD5(str);
//        System.out.println("\nMD5:"+(System.currentTimeMillis()-t1));
//        t1=System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++)
//            Base64Encode(str);
//        System.out.println("Base64:"+(System.currentTimeMillis()-t1));
//        t1=System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++)
//            AesEncrypt(str, PWD);
//        System.out.println("Aes:"+(System.currentTimeMillis()-t1));
//        t1=System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++)
//            desEncrypt(str, PWD);
//        System.out.println("Des:"+(System.currentTimeMillis()-t1));
//        t1=System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++)
//            threeDesEncrypt(str, PWD);
//        System.out.println("3Des:"+(System.currentTimeMillis()-t1));
//        //=======================================
//        t1=System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++)
//            Base64Decode(strBase64);
//        System.out.println("\nBase64:"+(System.currentTimeMillis()-t1));
//        t1=System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++)
//            AesDecrypt(strAes, PWD);
//        System.out.println("Aes:"+(System.currentTimeMillis()-t1));
//        t1=System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++)
//            desDecrypt(strDes, PWD);
//        System.out.println("Des:"+(System.currentTimeMillis()-t1));
//        t1=System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++)
//            threeDesDecrypt(str3Des, PWD);
//        System.out.println("3Des:"+(System.currentTimeMillis()-t1));
//    }
}
