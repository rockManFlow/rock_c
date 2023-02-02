package com.kuark.tool.base.encrypt.formal;

/*
 --------------------------------------------**********--------------------------------------------

 该算法于1977年由美国麻省理工学院MIT(Massachusetts Institute of Technology)的Ronal Rivest，Adi Shamir和Len Adleman三位年轻教授提出，并以三人的姓氏Rivest，Shamir和Adlernan命名为RSA算法，是一个支持变长密钥的公共密钥算法，需要加密的文件快的长度也是可变的!

 所谓RSA加密算法，是世界上第一个非对称加密算法，也是数论的第一个实际应用。它的算法如下：

 1.找两个非常大的质数p和q（通常p和q都有155十进制位或都有512十进制位）并计算n=pq，k=(p-1)(q-1)。

 2.将明文编码成整数M，保证M不小于0但是小于n。

 3.任取一个整数e，保证e和k互质，而且e不小于0但是小于k。加密钥匙（称作公钥）是(e, n)。

 4.找到一个整数d，使得ed除以k的余数是1（只要e和n满足上面条件，d肯定存在）。解密钥匙（称作密钥）是(d, n)。

 加密过程： 加密后的编码C等于M的e次方除以n所得的余数。

 解密过程： 解密后的编码N等于C的d次方除以n所得的余数。

 只要e、d和n满足上面给定的条件。M等于N。

 --------------------------------------------**********--------------------------------------------
 */

import com.kuark.tool.base.encrypt.unformal.ConfigureEncryptAndDecrypt;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO 非对称加密
 * 使用RSA加密应注意
 * 1、加密与解密应放到不同系统中
 * 2、生成密文的长度等于密钥长度。密钥长度越大，生成密文的长度也就越大，加密的速度也就越慢，而密文也就越难被破解掉。
 * 著名的"安全和效率总是一把双刃剑"定律，在这里展现的淋漓尽致。我们必须通过定义密钥的长度在"安全"和"加解密效率"之间做出一个平衡的选择。
 * 3、TODO 生成密文的长度和明文长度无关，但明文长度不能超过密钥长度---即明文长度不能超过KEYSIZE大小
 * TODO 不管明文长度是多少，RSA 生成的密文长度总是固定的。
 * TODO 但是明文长度不能超过密钥长度。比如 Java 默认的 RSA 加密实现不允许明文长度超过密钥长度减去 11(单位是字节，也就是 byte)。也就是说，如果我们定义的密钥(我们可以通过 java.security.KeyPairGenerator.initialize(int keysize)
 * 来定义密钥长度)长度为 1024(单位是位，也就是 bit)，生成的密钥长度就是 1024位 / 8位/字节 = 128字节，那么我们需要加密的明文长度不能超过 128字节 -
 * 11 字节 = 117字节。也就是说，我们最大能将 117 字节长度的明文进行加密，否则会出问题(抛诸如 javax.crypto.IllegalBlockSizeException: Data must not be longer than 53 bytes 的异常)。
 * 而 BC 提供的加密算法能够支持到的 RSA 明文长度最长为密钥长度。
 * <p/>
 * 4、byte[].toString() 返回的实际上是内存地址，不是将数组的实际内容转换为 String
 * 5、每次生成的密文都不一致证明你选用的加密算法很安全
 * 6、Java 默认的 RSA 实现 "RSA/None/PKCS1Padding" 要求最小密钥长度为 512 位(否则会报 java.security.InvalidParameterException: RSA keys must be at least 512 bits long 异常)，
 * 也就是说生成的密钥、密文长度最小为 64 个字节。如果你还嫌大，可以通过调整算法提供者来减小密文长度：
 * Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
 * final KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
 * keyGen.initialize(128);
 * 7、先生成一对密钥，供以后加解密使用(不需要每次加解密都生成一个密钥)
 */
public class RSA {
    /**
     * 指定key的大小
     */
    private static int KEYSIZE = 2048;
    static Map<String, String> map = null;
    static {
        //生成一个密钥对.只运行一次
        try {
            map = generateKeyPair();
            String publicKey = map.get("publicKey");
            FileOutputStream out1=new FileOutputStream("d:\\publicKey.key");
            out1.write(publicKey.getBytes());
            out1.close();
            String privateKey = map.get("privateKey");
            FileOutputStream out2=new FileOutputStream("d:\\privateKey.key");
            out2.write(privateKey.getBytes());
            out2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //使用RSA进行加密，每次生成的密文都不相同
    public static void main(String[] s) throws Exception {
        String data = "fengfeifeng-123jiadehfere84和平中国";
        //加密
//        String publicKey = map.get("publicKey");
        String publicKey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnjbD6lbg015B9Y0i0ztl8yREwqvE+BeBN0VpW4JoEbdvMikkEmz8y7/eX+DUfl3l7qxGA8mQcWQHQ23B5A+XxCV+Yx1Q2gHmhPeb8XAg0QpoEQTkBS4R4d3tdjCX5704jZ5OFXbNsIp0tGDSQy1IkJ1QsxHYmwF/sqx98JuPe6mRuu8P9CkWCerZQ64UMNREyYT1jDsZE3LeBLbuKfhUUbZO3V8dx2PR9IgwPaedULVmQOmJmqUR4O3TGzD8KivPbpj+h0/F7dte5rmIP0RV/fThdMC+oEWWIOshE3ey6qChduRhnyG2hFN63VFxTzuii0PR14BeeZENLfJlFzBlQQIDAQAB";
        String encryptData = encrypt(data, publicKey);
        //encryptData=Z6ZZ/EatYoNmwy4xG5ap3RkRW2Z+kPx3IhNvfWoWSL/V8v2IQ9E2VT/IqxC+MrJYpiu4HqXh6NZOOxgFMgnUKQ==
        System.out.println("encryptData=" + encryptData);
        //解密
//        String privateKey = map.get("privateKey");
        String privateKey ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCeNsPqVuDTXkH1jSLTO2XzJETCq8T4F4E3RWlbgmgRt28yKSQSbPzLv95f4NR+XeXurEYDyZBxZAdDbcHkD5fEJX5jHVDaAeaE95vxcCDRCmgRBOQFLhHh3e12MJfnvTiNnk4Vds2winS0YNJDLUiQnVCzEdibAX+yrH3wm497qZG67w/0KRYJ6tlDrhQw1ETJhPWMOxkTct4Etu4p+FRRtk7dXx3HY9H0iDA9p51QtWZA6YmapRHg7dMbMPwqK89umP6HT8Xt217muYg/RFX99OF0wL6gRZYg6yETd7LqoKF25GGfIbaEU3rdUXFPO6KLQ9HXgF55kQ0t8mUXMGVBAgMBAAECggEAUuGlqHFyvce3DgetorKPKdxqGQEa4kyN6IJ4OvQJyJ4rpj9fIjfuBl6xA9EoADr4ZRviYcGJ5ZzdITmyROLWQm7R0uJcpJgyRp3gF48B4gBtm9zQBtanj4dPCodSTJgXtDil9yCRiFY/5O6Dt6VVSkzfJa/HpQwX9WXfY9haRhEu0CnkjuBPZvF6O58ApZObB2CN+pWFGSLSw3rkUWRCVxigu1Ap8bXoxBcjBLp8BK8XqHO+SfmutTw2XlwY0hoVz1p6dJdsGxDVTioAlTxGpMlfux896YfIeI/Zd1u5666fm9CK6k5uUCyN+bXLld18wHqwpRA56jTRRMXzY6axQQKBgQDexA01fAMy8pTRy4exQj1yuRwajjXDNe37epARSr8v76YZQHLeZUEW12rN70uorGfBV/grPx36sWyjA4JO9yilBVDzC+9X2jJ40ZNcfO4bfwT7UQHmSKjhy1BRv9Vf+1BwCiZ1xiXQrqBCUiUDXuaY+aOrOlglfwA0C47WUmK5JQKBgQC10VNWZG0gF3qHJeEoSfmwyRFk8h+0TkNyUMiF/Ey10kcmo/wqXx/RvK/O9vTmXHVH6iPvCFeSb7L4mKIgMYd56u9Emz9CATCyEqYbD+EKndZN16LcNsK32fEsCHZwljAa6uBo+lmEq7aD/NBYfwOAl2LhB96WvB5of3ugWrmm7QKBgGsRWbPqLfNvyn1nUImu56/bLpnI3ig8JGzpRJM7FNcqXO/oHQ2tJvKeiPP/3qJsctXnvRy0EmmqmydiiWAAxSVDf9vBUcEs23Ncb3JVjW/jxfYwwjP3mkwkFy+jfx/Jv6iKCGA043oAZIBb0PRkyXqfCb5DpZgoCWX6ZCW4pUCNAoGAUUfdC7glmL0mR5jFiW9YspGQtyIOzlpWiJpYqp1Z5ca1jC6vInoYDXle1xVL8lfPDnE70zZVf99zkPMbN20s9XHzuSX2J5nEnTb9DwgQQODC11XZNr08W1iRGnBGEprxsnbiD8wrjMX++A5jTcyqUihbSeJsvdInfevP5qXobRkCgYEAxqTlPArTyiWtYm4CWx8bI6+1F+ZinlLMgccIsZd2QipUITTEAmS+T4u9p/HHff9HHFcIKqhEZW7occ+lAu+u4z4y2sG4A4s8lWiacQkyhiuxdBJu7GNfqnrjhiGVw9l8OQq+8kMp8OWEMhQ3bLrYlAIq0ruBQn4z31Y2Q3pw4Mk=";
        String decryptData = decrypt(encryptData, privateKey);
        System.out.println("decryptData=" + decryptData);
    }

    /**
     * 生成密钥对
     */
    public static Map<String, String> generateKeyPair() throws Exception {
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象，密钥对生成器 */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        kpg.initialize(KEYSIZE, sr);//KEYSIZE设置生成密钥长度
        /** 生成密匙对 */
        final KeyPair kp = kpg.generateKeyPair();
        /** 得到公钥 */
        Key publicKey = kp.getPublic();
        byte[] publicKeyBytes = publicKey.getEncoded();
        String pub = new String(Base64.encodeBase64(publicKeyBytes),
                ConfigureEncryptAndDecrypt.CHAR_ENCODING);
        /** 得到私钥 */
        Key privateKey = kp.getPrivate();
        byte[] privateKeyBytes = privateKey.getEncoded();
        String pri = new String(Base64.encodeBase64(privateKeyBytes),
                ConfigureEncryptAndDecrypt.CHAR_ENCODING);
        Map<String, String> map = new HashMap<String, String>();
        map.put("publicKey", pub);
        map.put("privateKey", pri);
        RSAPublicKey rsp = (RSAPublicKey) kp.getPublic();
        BigInteger bint = rsp.getModulus();
        byte[] b = bint.toByteArray();
        byte[] deBase64Value = Base64.encodeBase64(b);
        String retValue = new String(deBase64Value);
        map.put("modulus", retValue);
        return map;
    }

    /**
     * 加密方法 source： 源数据
     */
    public static String encrypt(String source, String publicKey)
            throws Exception {
        Key key = getPublicKey(publicKey);
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ConfigureEncryptAndDecrypt.RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        return new String(Base64.encodeBase64(b1),ConfigureEncryptAndDecrypt.CHAR_ENCODING);
    }

    /**
     * 解密算法 cryptograph:密文
     */
    public static String decrypt(String cryptograph, String privateKey)
            throws Exception {
        Key key = getPrivateKey(privateKey);
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ConfigureEncryptAndDecrypt.RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] b1 = Base64.decodeBase64(cryptograph.getBytes());
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    /**
     * 得到公钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                Base64.decodeBase64(key.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
                Base64.decodeBase64(key.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    public static String sign(String content, String privateKey) {
        String charset = ConfigureEncryptAndDecrypt.CHAR_ENCODING;
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.decodeBase64(privateKey.getBytes()));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(priKey);
            signature.update(content.getBytes(charset));
            byte[] signed = signature.sign();
            return new String(Base64.encodeBase64(signed));
        } catch (Exception e) {
        }
        return null;
    }

//    public static boolean checkSign(String content, String sign, String publicKey) {
//        try {
//            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//            byte[] encodedKey = Base64.decode2(publicKey);
//            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
//            Signature signature = Signature.getInstance("SHA1WithRSA");
//            signature.initVerify(pubKey);
//            signature.update(content.getBytes("utf-8"));
//            boolean bverify = signature.verify(Base64.decode2(sign));
//            return bverify;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}