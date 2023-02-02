package com.kuark.tool.model.connection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by caoqingyuan on 2016/11/11.
 */
public class Client {
    private static final Logger logger= LoggerFactory.getLogger(Client.class);
    @Test
    public void sends(){
        String url="http://127.0.0.1:8080/mod/zz/callback.do";
        //验算接收字节数组的大小与信息的完整性,这种方式会导致接收汉字和英文的字节大小导致的信息不正确
        String context="中国世俗a是得是好";
//        try {
//            String s = HttpConnect.httpConnection(url, context.getBytes(), 30);
//            logger.info("result="+s);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    @Test
    public void tt(){
        //String 的长度是按照个数来算的，byte是按照字节来算的大小
        String context="ceshi测试";
        System.out.println("size="+context.length());
        byte[] bytes=context.getBytes();
        System.out.println("byte size="+bytes.length);
    }
}
