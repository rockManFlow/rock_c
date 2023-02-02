package com.kuark.tool.model.nio.exceple;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by caoqingyuan on 2017/8/1.
 */
public class Server {

    public static void main(String[] args) throws Exception {
        ByteBuffer echoBuffer = ByteBuffer.allocate(8);
        //创建服务器套接字通道,尚未绑定
        ServerSocketChannel ssc = ServerSocketChannel.open();
        //创建选择器
        Selector selector = Selector.open();
        //设置通道非阻塞模式
        ssc.configureBlocking(false);
        //获取与此通道关联的服务器套接字
        ServerSocket ss = ssc.socket();
        //实现 IP 套接字地址
        InetSocketAddress address = new InetSocketAddress(8080);
        //绑定特定端口和ip
        ss.bind(address);
        //向给定的选择器注册此通道，返回一个选择键。
        SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("开始监听……");
        while (true) {
            System.out.println("监听循环");
            //选择一组键,会一直阻塞，直到有一个可用通道才返回
            int num = selector.select();
            System.out.println("num:"+num);
            //返回此选择器的已选择键集
            Set selectedKeys = selector.selectedKeys();
            Iterator it = selectedKeys.iterator();
            System.out.println("ddddd");
            while (it.hasNext()) {
                //每次向选择器注册通道时就会创建一个选择键---对应通道
                SelectionKey sKey = (SelectionKey) it.next();
                SocketChannel channel = null;
                //测试此键的通道是否已准备好接受新的套接字连接
                if (sKey.isAcceptable()) {
                    ServerSocketChannel sc = (ServerSocketChannel) key.channel();
                    channel = sc.accept();// 接受连接请求
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                    it.remove();
                } else if (sKey.isReadable()) {
                    channel = (SocketChannel) sKey.channel();
                    while (true) {
                        echoBuffer.clear();
                        int r = channel.read(echoBuffer);
                        System.out.println("r 是否读到末尾："+r);
                        if (r <= 0) {
                            channel.close();
                            System.out.println("接收完毕，断开连接");
                            break;
                        }
                        System.out.println("##" + r + " " + new String(echoBuffer.array(), 0, echoBuffer.position()));
                        echoBuffer.flip();
                    }
                    it.remove();
                } else {
                    channel.close();
                }
            }
        }
    }
}
