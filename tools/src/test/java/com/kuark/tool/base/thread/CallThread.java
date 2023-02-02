package com.kuark.tool.base.thread;

import com.alibaba.fastjson.JSONObject;
import com.kuark.tool.base.http.HttpURLConnection;
import lombok.extern.slf4j.Slf4j;

/**
 * @author caoqingyuan
 * @detail
 * @date 2019/7/24 17:02
 */
@Slf4j
public class CallThread implements Runnable {

    private String param;

    public CallThread(String param){
        this.param=param;
    }
    @Override
    public void run() {
        try {
            log.info("Client Send Req={}", param);
            JSONObject req = new JSONObject();
            req.put("reqInfo", param);
//            SimpleHttpClient.gets("http://127.0.0.1:3300/stressMvc.do?reqInfo="+param);
//            SimpleHttpClient.posts("http://127.0.0.0:3300/stressMvc.do",3300,null,param);
            byte[] bytes = HttpURLConnection.postBinResource("http://127.0.0.1:3300/stressMvc.do", param, "UTF-8", 10);
            log.info("Result={}",new String(bytes,"UTF-8").toString());
        }catch (Exception e){
            log.error("",e);
        }
    }

    public static void main(String[] args) throws Exception {

        JSONObject req = new JSONObject();
        req.put("loanOuterNo", "1111111");
        req.put("loanAmount","10000");
        req.put("loanDate","2019-07-02 11:10:10");
        req.put("resultMsg","成功");
//        req.put("outerRepayNo","2222wwww");
//        req.put("repayMode","2");
        byte[] bytes = HttpURLConnection.postBinResource("http://127.0.0.1:3311/trade/credit/callback", req, "UTF-8", 10);
        log.info("Result={}",new String(bytes,"UTF-8").toString());
    }
}
