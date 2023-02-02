package com.kuark.tool;

import com.kuark.tool.base.http.HttpURLConnection;

/**
 * @author rock
 * @detail
 * @date 2019/8/26 20:37
 */
public class HttpTest {
    public static void main(String[] args) throws Exception {
        System.out.println("eeee");
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("key","11111");
        HttpURLConnection.postBinResource("http://127.0.0.1:8080/trade/zazadback/callback","OOOO","UTF-8",20);
    }
}
