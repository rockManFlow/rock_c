package com.kuark.tool.model.nio.exceple;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by caoqingyuan on 2017/8/1.
 */
public class ServerDemo {
    public static void main(String[] s) throws IOException {
        System.out.println("start");
        ServerSocket server=new ServerSocket(8083);
        //创建与此客户端对话的socket
        Socket accept = server.accept();
        DataInputStream inputStream = new DataInputStream (accept.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(accept.getOutputStream());
        System.out.println("reserve="+inputStream.readUTF());
        outputStream.writeUTF("OK");
        accept.close();
        server.close();
        System.out.println("end");
    }
}
