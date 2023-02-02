package com.kuark.tool.base.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by caoqingyuan on 2016/9/18.
 * 抓取指定的网页内容
 */
public class GraepWeb {
    @Test
    public void main(){
        try {
            getHttp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void getHttp() throws IOException {
        String outUrl="e:/grasp.html";
        HttpClient client=new HttpClient();
        String url="http://www.360doc.com/content/14/0607/16/203871_384582921.shtml";
        HttpMethod method = new GetMethod(url);//HttpMethod post=new PostMethod("http://..");
        client.executeMethod(method);
        System.out.println("method.getStatusCode()=="+method.getStatusCode());
        System.out.println("=="+method.getResponseBodyAsString());
        //打印服务器返回的状态
        if(method.getStatusCode()==200){
            byte[] responseBody = method.getResponseBody();
            FileOutputStream out=new FileOutputStream(outUrl);
            out.write(responseBody);
        }
        //释放连接
        method.releaseConnection();
    }
}
