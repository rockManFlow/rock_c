package com.kuark.tool.base.encrypt;

import com.kuark.tool.base.encrypt.formal.AES;

/**
 * Created by caoqingyuan on 2017/4/26.
 */
public class AESTest {
    public static void main(String[] args) throws Exception {
        String content = "我爱你,祖国。ceshiwenban电视剧单位发金额为不符合我";
        System.out.println("加密前：" + content);

        String key = "JT0gdjs2dsh_deuh";
        System.out.println("加密密钥和解密密钥：" + key);

        String encrypt = AES.aesEncrypt(content, key);
        System.out.println("加密后：" + encrypt);

        String decrypt = AES.aesDecrypt(encrypt, key);
        System.out.println("解密后：" + decrypt);
    }
}
