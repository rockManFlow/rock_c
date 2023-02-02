package com.kuark.tool.advance.advance20200907.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * @author rock
 * @detail zk主要的就是存储和监听
 * 监听使用的zab 协议----当有改变，会通知所有的注册服务器
 * @date 2020/9/15 14:35
 */
public class ZookServer {
    private static final String connectString = "192.168.234.128:2181";
    private static final int sessionTimeout = 200000;
    private static final String parentNode = "/servers";

    private ZooKeeper zk = null;


    /**
     * 模拟服务器上线
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        // 获取zk连接
        ZookServer server = new ZookServer();
        server.getConnect();

        // 利用zk连接注册服务器信息
        server.registerServer("service01");

        // 启动业务功能
        server.handleBussiness("service01");

    }
    /**
     * 创建到zk的客户端连接
     *
     * @throws Exception
     */
    public void getConnect() throws Exception {

        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

            public void process(WatchedEvent event) {
                // 收到事件通知后的回调函数（应该是我们自己的事件处理逻辑）
                System.out.println(event.getType() + "---" + event.getPath());
                try {
                    zk.getChildren("/", true);
                } catch (Exception e) {
                }
            }
        });

    }

    /**
     * 向zk集群注册服务器信息
     *
     * @param hostname
     * @throws Exception
     */
    public void registerServer(String hostname) throws Exception {

        /**
         * 先判断注册节点的父节点是否存在，如果不存在，则创建持久节点
         */
        Stat exists = zk.exists("/servers", false);
        if(exists==null){
            zk.create("/servers",null,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }
        //临时的临时的序列化结点
        String create = zk.create(parentNode + "/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname + " is online.." + create);

    }

    /**
     * 业务功能
     *
     * @throws InterruptedException
     */
    public void handleBussiness(String hostname) throws InterruptedException {
        System.out.println(hostname + " start working.....");
        Thread.sleep(Long.MAX_VALUE);
    }

}
