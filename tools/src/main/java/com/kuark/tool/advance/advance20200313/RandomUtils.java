package com.kuark.tool.advance.advance20200313;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

/**
 * @author rock
 * @detail
 */
public class RandomUtils {
    /**
     * 生成指定length的随机字符串（A-Z，a-z，0-9）
     * @param num
     * @return
     */
    public static String randomStr(int num){
        return RandomStringUtils.randomAlphanumeric(num);
    }

    public static String getUuid(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static void main(String[] args) {
        String randomStr = randomStr(32);
        String uuid = getUuid();
        System.out.println("私钥："+randomStr);
        System.out.println("key："+uuid);
    }
}
