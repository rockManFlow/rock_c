package com.kuark.tool.model.controler;

import com.kuark.tool.base.http.URLConnection;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by caoqingyuan on 2016/11/25.
 */
@Controller
public class CallbackController {
    private static final Logger logger= LoggerFactory.getLogger(CallbackController.class);
    /**回调与回应测试实现**/
    @RequestMapping("/{mark}/cb/callback.do")
    public void callback(HttpServletRequest request, HttpServletResponse response,@PathVariable("mark") Integer mark) throws IOException {
        System.out.println("mark="+mark);
        logger.info("callback start");
        //获取输入流
        ServletInputStream inputStream = request.getInputStream();
        StringBuilder st = new StringBuilder();
        //用规定字节数组的大小从流中获取数据的字节流会有汉字和英文字节大小不一致，导致接收的信息不准确
        //引出一个问题，怎么从流中获取信息使数据准确？org.apache.common.io.IOUtils可以解决这个问题
        byte[] value = IOUtils.toByteArray(inputStream);
        logger.info("get info [" + new String(value,"UTF-8")+"]");
        ServletOutputStream outputStream =null;
        try {
            outputStream = response.getOutputStream();
            String context = "messageCode=0000&messageDetail=SUCCESS&detail=测试";
            outputStream.write(context.getBytes("UTF-8"));
        }catch (IOException i){
            logger.error("",i);
        }finally {
            if(outputStream!=null) {
                outputStream.flush();
                outputStream.close();
            }
        }
        logger.info("callback end");
    }
    @Test
    public void sendData(){
        String data="name=xiaohong&age=18&detail=详细信息";
        String url="http://127.0.0.1:8080/mod/1221/cb/callback.do";
        try {
            byte[] resp= URLConnection.postBinResource(url,data,"UTF-8",45);
            System.out.println(new String(resp,"UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
