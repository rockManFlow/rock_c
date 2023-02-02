package com.kuark.tool.advance.advance20201111.netty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * @author rock
 * @detail
 * @date 2021/3/12 15:13
 */
public class ServerMain extends Thread{
    ServerSocket server = null;
    Socket socket = null;

    public ServerMain(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

//        super.run();
        try {
            System.out.println(getdate() + "  等待客户端连接...");
            //会阻塞住，直到有连接为止
            socket = server.accept();
            new sendMessThread().start();// 连接并返回socket后，再启用发送消息线程
            System.out.println(getdate() + "  客户端 （" + socket.getInetAddress().getHostAddress() + "） 连接成功...");
            InputStream in = socket.getInputStream();
            int len = 0;
            System.out.println("server run mm1");
            byte[] buf = new byte[1024];
            //流的read返回-1是当到达流的末尾，不再有数据可用，才会返回-1.由于网络一直处于连接状态，始终到达不到流的尾部，导致一直不会返回-1
            //读取数据都是阻塞来读取的。注意当使用FileInputStream时，会返回-1 ，因为读取文件是有大小的，输入流读取完毕之后会有-1.但网络流不是
            //直到关闭连接流才到最后，才会返回-1，执行while之后的逻辑
            while ((len = in.read(buf)) != -1) {
                System.out.println(getdate() + "  客户端: （"
                        + socket.getInetAddress().getHostAddress() + "）说："
                        + new String(buf, 0, len, "UTF-8"));
            }
            System.out.println("server run end");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getdate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String result = format.format(date);
        return result;
    }

    class sendMessThread extends Thread {
        @Override
        public void run() {
//            super.run();
            Scanner scanner = null;
            OutputStream out = null;
            try {
                if (socket != null) {
                    scanner = new Scanner(System.in);
                    out = socket.getOutputStream();
                    String in = "";
                    //会一直循环，直到输入指定退出符号为止，关闭输出流
                    do {
                        in = scanner.next();
                        out.write(("" + in).getBytes("UTF-8"));
                        out.flush();// 清空缓存区的内容
                    } while (!in.equals("q"));
                    scanner.close();
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    // 函数入口
    public static void main(String[] args) {
        ServerMain server = new ServerMain(1234);
        server.start();
    }
}
