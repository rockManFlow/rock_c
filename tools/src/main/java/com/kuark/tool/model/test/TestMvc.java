package com.kuark.tool.model.test;


import com.kuark.tool.base.http.URLConnection;
import org.junit.Test;

/**
 * Created by caoqingyuan on 2017/5/10.
 */
public class TestMvc {
    public static void main(String[] st) throws Exception {
            String url="http://localhost:8080/mod/springAop/aop.do";//http://192.168.3.10:8080/mod/testMybatis.do
            byte[] bytes = URLConnection.postBinResource(url, "", "UTF-8", 30);
            System.out.println("response:"+new String(bytes,"UTF-8"));
    }
    @Test
    public void mmm(){
        String cardNo="622909219016582917";
        cardNo = cardNo.substring(6, 17);
        System.out.println(cardNo);
    }
}
