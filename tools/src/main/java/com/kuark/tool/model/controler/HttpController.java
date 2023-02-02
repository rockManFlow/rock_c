package com.kuark.tool.model.controler;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于验证http和HTTPS请求处理是否相同
 * Created by caoqingyuan on 2016/9/1.
 */
@RestController
public class HttpController {
    private static final Logger logger = Logger.getLogger(HttpController.class);

    //获取请求的IP地址
    @RequestMapping(value = "httpController/bingg.do")
    public void bingg(HttpServletRequest request, HttpServletResponse response) {
        int remotePort = request.getRemotePort();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        //获取访问的IP地址和端口
        logger.info("request ip=" + ip);
        logger.info("remotePort=" + remotePort);
    }

    @RequestMapping("httpController/processRequest.do")
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("start");
        request.setCharacterEncoding("UTF-8");
        StringBuffer requestURL = request.getRequestURL();
        logger.info("requestURL " + requestURL);
        String protocol = request.getProtocol();
        int serverPort = request.getServerPort();
        logger.info("protocol :" + protocol + " ||serverPort :" + serverPort);
        //获取输入流
        ServletInputStream inputStream = request.getInputStream();
        StringBuilder st = new StringBuilder();
        byte[] context = new byte[1024];
        while (inputStream.read(context) != -1) {
            st.append(context);
        }
        logger.info("get info " + st.toString());
        logger.info("end");
    }

    @Test
    public void tt(){
        StringBuilder bb=new StringBuilder();
        byte[] b=new byte[20];
        for(int i=0;i<b.length;i++){
            b[i]='s';
        }
        bb.append(b);
        System.out.println("bb="+bb.toString());
    }
}
