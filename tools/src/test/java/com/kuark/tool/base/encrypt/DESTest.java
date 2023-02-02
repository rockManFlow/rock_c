package com.kuark.tool.base.encrypt;

import com.kuark.tool.base.encrypt.formal.DES;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

/**
 * Created by caoqingyuan on 2017/4/26.
 */
public class DESTest {
    static String data = "中弩弓测试撒娇";
    static String key = "123456789012345678901234";//加密解密key

    public static void main(String[] args) {
        try {
            String jiami = DES.encrypt(data, key);
            System.out.println("加密:" + jiami);
            String decrypts = DES.decrypt(jiami,key);
            System.out.println("解密: " + decrypts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test(){
        System.out.println("start");
        try {
            String key = "001010110000000110010000100000011100101001010010";
            String middleUrl = "e:/JYMX_201412121000131502_20170312" + "_E.txt";
            String firstUrl = "e:/JYMX_201412121000131502_20170312.txt";
            String endUrl = "e:/JYMX_D.txt";
            System.out.println("加密:");
            String data = FileUtils.readFileToString(new File(firstUrl));
            String jiami = DES.encrypt(data, key);
            FileUtils.writeByteArrayToFile(new File(middleUrl), jiami.getBytes());
            System.out.println("解密: ");
            String data2 = FileUtils.readFileToString(new File(middleUrl));
            String decrypts = DES.decrypt(data2, key);
            FileUtils.writeByteArrayToFile(new File(endUrl), decrypts.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
