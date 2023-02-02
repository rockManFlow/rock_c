package com.kuark.tool.advance.advance20191228;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author rock
 * @detail HttpURLConnection是基于HTTP协议的，其底层通过socket通信实现
 * @date 2020/4/23 9:52
 */
public class HttpUrlConnectionResolver {
    public static void main(String[] args) throws IOException {
        //http路径返回的是HttpURLConnection，jar包路径返回的是JarURLConnection
        URL url =new URL("http://localhost:8080/TestHttpURLConnectionPro/index.jsp");
    }

    public static byte[] httpConnection(String url, byte[] context, Integer timeOut) throws IOException {
        //HttpURLConnection是基于HTTP协议的，其底层通过socket通信实现
        HttpURLConnection http = null;
        InputStream inputStream =null;
        try {
            URL urls = new URL(url);
            http = (HttpURLConnection) urls.openConnection();
            http.setRequestMethod("POST");
            http.setConnectTimeout(timeOut * 1000);
            http.setReadTimeout(timeOut * 1000);
            //打算使用URL的连接作为输入的连接对象
            http.setDoInput(true);
            http.setDoOutput(true);
            //是否使用连接使用缓存
            http.setUseCaches(false);
            //实际上只是建立了与服务器的TCP连接，并没有HTTP请求
            //connect()函数会根据HttpURLConnection对象的配置值生成http头部信息
            http.connect();
            //把要写的内容写到内存输出流中，这时并没有真正的发送
            OutputStream outputStream = http.getOutputStream();
            outputStream.write(context);
            outputStream.flush();
            outputStream.close();
            //判断这个是判断是否与服务器建立TCP连接成功
            if (http.getResponseCode() == 200) {
                // 调用HttpURLConnection连接对象的getInputStream()函数,
                // 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
                //发送HTTP请求并获取输入流
                /**
                 * 在http头后面紧跟着的是http请求的正文，正文的内容是通过outputStream流写入的，
                 * 实际上outputStream不是一个网络流，充其量是个字符串流，往里面写入的东西不会立即发送到网络，
                 * 而是存在于内存缓冲区中，待outputStream流关闭时，根据输入的内容生成http正文。
                 * 至此，http请求的东西已经全部准备就绪。在getInputStream()函数调用的时候，就会把准备好的http请求
                 * 正式发送到服务器了，然后返回一个输入流，用于读取服务器对于此次http请求的返回信息。由于http
                 * 请求在getInputStream的时候已经发送出去了（包括http头和正文），因此在getInputStream()函数
                 * 之后对connection对象进行设置（对http头的信息进行修改）或者写入outputStream（对正文进行修改）
                 * 都是没有意义的了，执行这些操作会导致异常的发生。
                 */
                inputStream = http.getInputStream();
                byte[] bytes= IOUtils.toByteArray(inputStream);
                return bytes;
            } else {
                System.out.println("http connect[" + http.getResponseCode() + "]");
                return null;
            }
        } finally {
            if (http != null)
                http.disconnect();
            IOUtils.closeQuietly(inputStream);
        }
    }
}
