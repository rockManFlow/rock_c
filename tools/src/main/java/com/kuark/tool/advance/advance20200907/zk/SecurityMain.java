package com.kuark.tool.advance.advance20200907.zk;

import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.security.*;

/**
 * @author rock
 * @detail
 * @date 2020/10/27 14:13
 */
public class SecurityMain {
    public static void main(String[] args) throws NoSuchProviderException, NoSuchAlgorithmException
            , NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        for(Provider p : Security.getProviders()){
            System.out.println(p.getName()+"=="+p.getInfo());
        }

        //秘钥生成器
        KeyGenerator kg = KeyGenerator.getInstance("DES","SunJCE");
        //随机秘钥对象
        SecretKey secretKey = kg.generateKey();

        /**
         * 真正实现对数据加解密功能的类是Cipher，此类为加密和解密提供密码功能，它构成了 Java Cryptographic Extension (JCE) 框架的核心
         * s:它描述为产生某种输出而在给定的输入上执行的操作（或一组操作）,“算法/模式/填充”或“算法”（这种情况，使用模式和填充方案特定于Provider的默认值）
         */
        //DES加密算法，CBC的反馈模式，PKCS5Padding的填充方案
        Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding","SunJCE");

        /**
         * Cipher的init方法负责初始化，初始化需要指定模式和密钥，密钥本文采用上面已经生成的key，
         * 模式有四种ENCRYPT_MODE、DECRYPT_MODE、WRAP_MODE、UNWRAP_MODE分别表示加密、解密、密钥包装或密钥解包。
         * 如果加密则采用方法init(Cipher.ENCRYPT_MODE, key)；
         * 如果解密则采用方法init(Cipher.DECRYPT_MODE, key)；
         * WRAP_MODE、UNWRAP_MODE模式是用来实现数字信封用的，本文不作介绍。
         * 通常对数据进行加解密，使用方法doFinal(byte[] input)，传入参数是byte数组。
         * 为了在http协议下快速传输数据，且某些系统中只能使用ASCII字符，通常采用Base64编码。Base64就是用来将非ASCII字符的数据转换成ASCII字符的一种方法。还可以提高可视性。
         */
        //指定模式和秘钥
        c.init(Cipher.ENCRYPT_MODE,secretKey);

        //加解密操作数据
        byte[] bytes = c.doFinal("xxxxx".getBytes());

        //便于传输base64
        String targetString = new BASE64Encoder().encode(bytes);

        System.out.println("输出加密之后的base64密文串："+targetString);

    }
}
