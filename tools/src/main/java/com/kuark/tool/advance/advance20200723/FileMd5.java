package com.kuark.tool.advance.advance20200723;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author rock
 * @detail 文件MD5
 * @date 2020/8/7 15:02
 */
public class FileMd5 {
    /**
     * 一种方式
     * @param path
     * @return
     */
    public static String getMD5Three(String path) {
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            File f = new File(path);
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                //可以一段一段往里面添加待加密的内容
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi.toString(16);
    }

    /**
     * 文件分次MD5
     * @param path
     * @return
     * @throws IOException
     */
    public static String fileMd5Str(String path) throws IOException {
        return DigestUtils.md5Hex(new FileInputStream(path));
    }
}
