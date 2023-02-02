package com.kuark.tool.model.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 在页面中展示后台图片，不用下载，直接在页面中展示
 * Created by caoqingyuan on 2017/3/8.
 */
@Controller
public class ImagesController {
    //TODO 这个反应出一个问题就是谁请求的response中的地址就是原请求的
    @RequestMapping("image/getPage.do")
    public void mod(HttpServletResponse response){
        System.out.println("start");
        response.setContentType("img/jpeg");
        response.setCharacterEncoding("utf-8");
        try {
            OutputStream outputStream=response.getOutputStream();
            InputStream in= new FileInputStream(new File("E:/abab.jpg"));
            int len=0;
            byte[]buf=new byte[1024];
            while((len=in.read(buf,0,1024))!=-1){
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end");
    }
}
