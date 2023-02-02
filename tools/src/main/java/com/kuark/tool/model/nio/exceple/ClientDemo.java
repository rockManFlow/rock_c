package com.kuark.tool.model.nio.exceple;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by caoqingyuan on 2017/8/1.
 */
public class ClientDemo {
    public static void main(String[] s) throws IOException {
        System.out.println("start1");
        Socket socket=new Socket("localhost",8083);
        DataInputStream inputStream = new DataInputStream (socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
        outputStream.writeUTF("textInfo test");
        System.out.println(inputStream.readUTF());
        socket.close();
        System.out.println("end");
    }
}
