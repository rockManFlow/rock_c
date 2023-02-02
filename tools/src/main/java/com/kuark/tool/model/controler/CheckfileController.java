package com.kuark.tool.model.controler;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 实现获取对账文件的接口（基本流程）
 * Created by caoqingyuan on 2017/3/13.
 */
@Controller
public class CheckfileController {
    private static final Logger logger=LoggerFactory.getLogger(CheckfileController.class);
    @RequestMapping("download/checkfile")
    public void checkfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InputStream inputStream=null;
        OutputStream outputStream =null;
        try {
            inputStream=request.getInputStream();
            byte[] value = IOUtils.toByteArray(inputStream);
            String datas=new String(value,"UTF-8");
            logger.info("get info ["+datas+"]");
            //读取文件
            String fileString=readFile();
            logger.info("size="+fileString.length());
            outputStream = response.getOutputStream();
            outputStream.write(fileString.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            logger.error("",e);
        }finally {
            IOUtils.closeQuietly(inputStream);
            if(outputStream!=null){
                outputStream.close();
            }
        }
    }
    //读取指定的文件并base64编码，返回
    public String readFile() throws IOException {
        FileInputStream fileInputStream=null;
        byte[] value;
        try {
            fileInputStream = new FileInputStream(new File("e:\\JYMX_201412121000131502_20170312.txt"));
            value = IOUtils.toByteArray(fileInputStream);
            logger.info("byteSize="+value.length);
        }catch (IOException e){
            throw e;
        }finally {
            if(fileInputStream!=null){
                fileInputStream.close();
            }
        }
        return new BASE64Encoder().encode(value);
    }
}
