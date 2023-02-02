package com.kuark.tool.base.encrypt.formal;

import com.kuark.tool.base.encrypt.unformal.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * Created by cc on 2016/5/20.
 * 使用DES加密解密---可以指定一个key，已这个key进行加密解密
 * 属于对称加密
 * TODO 用法和AES相似
 */
public class DES {
    private final static String DES = "DES";

    //对string进行BASE64Encoder转换
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        Base64 base64en = new Base64();
        String strs = new String(base64en.encode(bt));
        return strs;
    }

    //加密代码
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();//创建随机数生成器，用于生成随机数
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);//指定一个 DES 密钥
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);//创建一个des秘钥生成工厂

        SecretKey securekey = keyFactory.generateSecret(dks);//把指定的key转换成一个des秘钥
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象

        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);

    }

    public static String decrypt(String date, String key) throws Exception {
        Base64 base64 = new Base64();
        byte[] bytes = base64.decode(date.getBytes());
        return new String(decrypt(bytes, key.getBytes()));
    }

    //解密代码
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源

        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象

        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作

        Cipher cipher = Cipher.getInstance(DES);

        // 用密钥初始化Cipher对象

        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }
}
