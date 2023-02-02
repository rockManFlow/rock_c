package com.kuark.tool.base.encrypt;

import com.kuark.tool.base.encrypt.formal.EncodeUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by caoqingyuan on 2017/4/26.
 */
public class EncodeUtilTest {
    @Test
    public void test() {
        String name = "曹庆远";
        String s1 = EncodeUtils.StrToHex(name);
        System.out.println("指定编码：" + s1);
        String s2 = null;
        try {
            s2 = EncodeUtils.HexToStr(s1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("解码："+s2);
    }
}
