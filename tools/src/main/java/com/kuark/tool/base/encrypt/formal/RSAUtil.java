package com.kuark.tool.base.encrypt.formal;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author caoqingyuan
 * @detail RSA进行加解密
 * @date 2018/3/15 15:08
 */
public class RSAUtil {
    //RSA算法模式
    private static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";

    /**
     * 加密
     * @param source 源数据
     * @param publicKey 公钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String source,String publicKey) throws Exception {
        Key key = getPublicKey(publicKey);
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        return new String(Base64.encodeBase64(b1),"UTF-8");
    }


    /**
     * 解密
     * @param encryptData 目标数据
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String decode(String encryptData,String privateKey) throws Exception {
        Key key = getPrivateKey(privateKey);
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] b1 = Base64.decodeBase64(encryptData.getBytes());
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    /**
     * 得到公钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    private static PublicKey getPublicKey(String key) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                Base64.decodeBase64(key));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 得到私钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    private static PrivateKey getPrivateKey(String key) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
                Base64.decodeBase64(key.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    //使用RSA进行加密，每次生成的密文都不相同
    public static void main(String[] s) throws Exception {
        String data = "fengfeifeng-123jiadehfere84和平中国";
        //加密
        String publicKey = new String(IOUtils.toByteArray(new FileInputStream("d:\\publicKey.key")));
        String encryptData = encrypt(data, publicKey);
        System.out.println("encryptData=" + encryptData);
        //解密
        String privateKey  = new String(IOUtils.toByteArray(new FileInputStream("d:\\privateKey.key")));
        String decryptData = decode(encryptData, privateKey);
        System.out.println("decryptData=" + decryptData);
    }
}
